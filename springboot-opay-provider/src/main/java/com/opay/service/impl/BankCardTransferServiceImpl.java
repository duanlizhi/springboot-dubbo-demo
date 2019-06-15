package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.constant.ErrorEnum;
import com.opay.constant.TransferStatusEnum;
import com.opay.entity.AccountDo;
import com.opay.entity.TransactionRecordDo;
import com.opay.entity.TransferDTO;
import com.opay.exception.CustomerException;
import com.opay.group.Charge;
import com.opay.service.AccountService;
import com.opay.service.TransactionRecordService;
import com.opay.service.TransferService;
import com.opay.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>银行卡转账或者充值接口</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 17:59</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Slf4j
@Service(interfaceClass = TransferService.class,group = "bankCardTransfer")
@Component
public class BankCardTransferServiceImpl implements TransferService {
    @Resource
    private AccountService accountService;
    @Resource
    private TransactionRecordService transactionRecordService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerException transfer(TransferDTO transferDTO) {
        log.info("通过银行卡进行交易，交易发起人id：{}, 银行卡id：{}, 交易类型：{}, 交易单号：{}",
                transferDTO.getFromAccountId(),transferDTO.getBankCardId(),
                transferDTO.getType(),transferDTO.getOrderNo());
        try {
            ValidatorUtil.validate(transferDTO, Charge.class);

        } catch (CustomerException e) {
            log.info("使用银行卡进行交易校验失败, errMsg: {}",e.getMsg());
            return CustomerException.builder().code(ErrorEnum.PARAMS_NOT_NULL.getCode())
                    .msg(ErrorEnum.PARAMS_NOT_NULL.getMsg()).build();
        }
        Lock lock = new ReentrantLock();
        lock.lock();

        try {
            //验证用户是否存在
            AccountDo account = accountService.getAccountByIdOrIdCard(transferDTO.getFromAccountId(),
                    null);
            if(null == account) {
                log.info("银行卡充值，用户不存在，用户id： {},交易单号： {}",transferDTO.getFromAccountId(),
                        transferDTO.getOrderNo());
                return CustomerException.builder().code(ErrorEnum.ACCOUNT_NOT_EXIT.getCode())
                        .msg(ErrorEnum.ACCOUNT_NOT_EXIT.getMsg()).build();
            }
            //查询该单号是否已经存在记录
            TransactionRecordDo transactionRecord = new TransactionRecordDo();
            transactionRecord.setOrderNo(transferDTO.getOrderNo());
            transactionRecord.setFromAccountId(transferDTO.getFromAccountId());
            TransactionRecordDo getTransactionRecord = transactionRecordService.getTransactionRecord(transactionRecord);
            if(null != getTransactionRecord &&
                    TransferStatusEnum.SUCCESS.getCode() == getTransactionRecord.getStatus()) {
                log.info("该充值交易已经提交，重复提交，交易单号： {}",getTransactionRecord.getOrderNo());
                return CustomerException.builder().code(ErrorEnum.REPEAT_COMMIT.getCode())
                        .msg(ErrorEnum.REPEAT_COMMIT.getMsg()).build();
            }
            transactionRecord.setStatus(TransferStatusEnum.PROCESSING.getCode());
            transactionRecord.setType(transferDTO.getType());
            transactionRecord.setChannel(transferDTO.getChannel());
            transactionRecord.setAmount(transferDTO.getAmount());
            transactionRecord.setBankCardId(transferDTO.getBankCardId());
            CustomerException customerException = transactionRecordService.save(transactionRecord);
            if(customerException.getCode() != ErrorEnum.SUCCESS.getCode()) {
                return customerException;
            }
            //现实中跟银行进行通信，使用异步任务去处理，后期更新订单，这里直接更新充值成功
            TransactionRecordDo transactionRecord_new = new TransactionRecordDo();
            transactionRecord_new.setOrderNo(transferDTO.getOrderNo());
            transactionRecord_new.setFromAccountId(transferDTO.getFromAccountId());
            TransactionRecordDo transactionRecord_update = transactionRecordService.getTransactionRecord(transactionRecord_new);
            if(null == transactionRecord_update) {
                log.info("为查询交易订单信息，交易单号：{}",transferDTO.getOrderNo());
                return CustomerException.builder().code(ErrorEnum.ORDER_NO_QUERY.getCode())
                        .msg(ErrorEnum.ORDER_NO_QUERY.getMsg()).build();
            }
            transactionRecord_update.setStatus(TransferStatusEnum.SUCCESS.getCode());
            transactionRecordService.updateByIdOrOrderNo(transactionRecord_update);
            accountService.increase(transferDTO.getFromAccountId(),account.getBalance().add(transferDTO.getAmount()));
            return CustomerException.builder().code(ErrorEnum.SUCCESS.getCode())
                    .msg(ErrorEnum.SUCCESS.getMsg()).build();
        } catch (DataAccessException e) {
            log.info("银行卡充值失败，交易单号：{},充值用户id：{}, 充值金额：{}, errMsg: {}",transferDTO.getOrderNo(),
                    transferDTO.getFromAccountId(),transferDTO.getAmount(),e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
        }finally {
            lock.unlock();
        }
    }
}
