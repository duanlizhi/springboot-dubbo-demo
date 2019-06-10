package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.constant.ErrorEnum;
import com.opay.constant.TransferStatusEnum;
import com.opay.constant.TransferTypeEnum;
import com.opay.entity.AccountDo;
import com.opay.entity.TransactionRecordDo;
import com.opay.entity.TransferDTO;
import com.opay.exception.CustomerException;
import com.opay.group.Transfer;
import com.opay.service.AccountService;
import com.opay.service.TransactionRecordService;
import com.opay.service.TransferService;
import com.opay.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>余额转账接口实现类</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 17:54</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Slf4j
@Service(interfaceClass = TransferService.class,group = "balanceTransfer")
@Component
public class BalanceTransferServiceImpl implements TransferService {
    @Resource
    private AccountService accountService;
    @Resource
    private TransactionRecordService transactionRecordService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerException transfer(TransferDTO transferDTO) {
        log.info("通过账户余额进行交易，交易发起人id：{},交易接受人id：{}, 交易类型：{}, 交易单号：{}",
                transferDTO.getFromAccountId(),transferDTO.getToAccountId(),
                transferDTO.getType(),transferDTO.getOrderNo());
        try {
            ValidatorUtil.validate(transferDTO, Transfer.class);

        } catch (CustomerException e) {
            log.info("使用账户余额进行交易校验失败, errMsg: {}",e.getMsg());
            return CustomerException.builder().code(ErrorEnum.PARAMS_NOT_NULL.getCode())
                    .msg(ErrorEnum.PARAMS_NOT_NULL.getMsg()).build();
        }
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            //验证用户是否存在及余额是否足够
            AccountDo formAccount = accountService.getAccountByIdOrIdCard(transferDTO.getFromAccountId(),
                    null);
            AccountDo toAccount = accountService.getAccountByIdOrIdCard(transferDTO.getToAccountId(), null);
            if(null == formAccount || null == toAccount) {
                log.info("余额转账交易，用户不存在，用户id：{}, 交易单号： {}",transferDTO.getFromAccountId(),
                        transferDTO.getOrderNo());
                return CustomerException.builder().code(ErrorEnum.ACCOUNT_NOT_EXIT.getCode())
                        .msg(ErrorEnum.ACCOUNT_NOT_EXIT.getMsg()).build();
            }
            //如果是转账操作，账户余额比交易金额要小，返回余额不足错误
            if(TransferTypeEnum.TRANSFER.getNum() == transferDTO.getType()) {
                if(formAccount.getBalance().compareTo(transferDTO.getAmount()) == -1) {
                    log.info("余额转账操作失败，余额不足");
                    return CustomerException.builder().code(ErrorEnum.BALANCE_NOT_ENOUGH.getCode())
                            .msg(ErrorEnum.BALANCE_NOT_ENOUGH.getMsg()).build();
                }
                //查询该单号是否已经存在记录
                TransactionRecordDo transactionRecord = new TransactionRecordDo();
                transactionRecord.setOrderNo(transferDTO.getOrderNo());
                transactionRecord.setFromAccountId(transferDTO.getFromAccountId());
                TransactionRecordDo getTransactionRecord = transactionRecordService.getTransactionRecord(transactionRecord);
                if(null != getTransactionRecord &&
                        TransferStatusEnum.SUCCESS.getCode() == getTransactionRecord.getStatus()) {
                    log.info("该转账交易已经提交，重复提交，交易单号： {}",getTransactionRecord.getOrderNo());
                    return CustomerException.builder().code(ErrorEnum.REPEAT_COMMIT.getCode())
                            .msg(ErrorEnum.REPEAT_COMMIT.getMsg()).build();
                }
                //余额充足，进行转账操作
                //交易发起者进行扣款操作
                BigDecimal money = formAccount.getBalance().subtract(transferDTO.getAmount());
                boolean decrease = accountService.decrease(transferDTO.getFromAccountId(), money);
                if(decrease) {
                    log.info("扣减余额成功");
                }else {
                    log.info("扣减余额失败");
                }
                //给转账目的账户增加余额
                BigDecimal add = toAccount.getBalance().add(transferDTO.getAmount());
                boolean increase = accountService.increase(transferDTO.getToAccountId(), add);
                if(increase) {
                    log.info("增加余额成功");
                }else {
                    log.info("增加余额失败");
                }
                //保存交易记录
                transactionRecord.setOrderNo(transferDTO.getOrderNo());
                transactionRecord.setFromAccountId(transferDTO.getFromAccountId());
                transactionRecord.setToAccountId(transferDTO.getToAccountId());
                transactionRecord.setStatus(TransferStatusEnum.SUCCESS.getCode());
                transactionRecord.setAmount(transferDTO.getAmount());
                transactionRecord.setChannel(transferDTO.getChannel());
                transactionRecord.setType(transferDTO.getType());
                transactionRecordService.save(transactionRecord);
            }
            return CustomerException.builder().code(ErrorEnum.SUCCESS.getCode()).
                    msg(ErrorEnum.SUCCESS.getMsg()).build();
        } finally {
            lock.unlock();
        }
    }
}
