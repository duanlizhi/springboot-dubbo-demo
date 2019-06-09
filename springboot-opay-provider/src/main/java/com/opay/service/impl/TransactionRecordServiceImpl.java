package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.dao.TransactionRecordDao;
import com.opay.entity.TransactionRecordDo;
import com.opay.exception.CustomerException;
import com.opay.service.TransactionRecordService;
import com.opay.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>交易记录service实现类</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 16:40</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Slf4j
@Service(interfaceClass = TransactionRecordService.class)
@Component
public class TransactionRecordServiceImpl implements TransactionRecordService {
    @Resource
    private TransactionRecordDao transactionRecordDao;
    @Override
    public Boolean save(TransactionRecordDo transactionRecordDo) {
        boolean flag = false;
        try {
            ValidatorUtil.validate(transactionRecordDo);
            transactionRecordDao.save(transactionRecordDo);
            flag = true;
        } catch (CustomerException e) {
            log.info("保存交易记录失败：交易单号：{}, 交易发起人id：{}, 交易类型：{}, 交易状态： {},交易金额： {}, " +
                    "errMsg: {}", transactionRecordDo.getOrderNo(),transactionRecordDo.getFromAccountId(),
                    transactionRecordDo.getType(),transactionRecordDo.getStatus(),transactionRecordDo.getAmount(),
                    e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean updateByIdOrOrderNo(TransactionRecordDo transactionRecordDo) {
        boolean flag = false;
        try {
            ValidatorUtil.validate(transactionRecordDo);
            transactionRecordDao.updateByIdOrOrderNo(transactionRecordDo);
            flag = true;
        } catch (CustomerException e) {
            log.info("更新交易记录失败：交易单号：{}, 交易发起人id：{}, 交易类型：{}, 交易状态： {},交易金额： {}, " +
                            "errMsg: {}", transactionRecordDo.getOrderNo(),transactionRecordDo.getFromAccountId(),
                    transactionRecordDo.getType(),transactionRecordDo.getStatus(),transactionRecordDo.getAmount(),
                    e.getMessage());
        }
        return flag;
    }

    @Override
    public TransactionRecordDo getTransactionRecord(TransactionRecordDo transactionRecordDo) {
        log.info("查询交易记录，交易单号：{},交易发起人id：{},交易类型: {},交易状态: {}",transactionRecordDo.getOrderNo(),
                transactionRecordDo.getFromAccountId(),transactionRecordDo.getType(),transactionRecordDo.getStatus());
        List<TransactionRecordDo> transactionRecordDos = transactionRecordDao.listTransactionRecord(transactionRecordDo);
        if(null != transactionRecordDos && transactionRecordDos.size() > 0) {
            return transactionRecordDos.get(0);
        }
        return null;
    }

    @Override
    public List<TransactionRecordDo> listTransactionRecord(TransactionRecordDo transactionRecordDo) {
        log.info("查询交易记录，交易单号：{},交易发起人id：{},交易类型: {},交易状态: {}",transactionRecordDo.getOrderNo(),
                transactionRecordDo.getFromAccountId(),transactionRecordDo.getType(),transactionRecordDo.getStatus());
        return transactionRecordDao.listTransactionRecord(transactionRecordDo);
    }
}
