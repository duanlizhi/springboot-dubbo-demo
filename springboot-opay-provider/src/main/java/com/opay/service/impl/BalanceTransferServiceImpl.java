package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.entity.TransactionRecordDo;
import com.opay.service.TransferService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Service(interfaceClass = TransferService.class,group = "balanceTransfer")
@Component
public class BalanceTransferServiceImpl implements TransferService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionRecordDo transfer(TransactionRecordDo transactionRecord) {
        return null;
    }
}
