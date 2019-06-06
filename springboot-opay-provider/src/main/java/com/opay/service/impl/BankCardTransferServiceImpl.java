package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.entity.TransactionRecordDo;
import com.opay.service.TransferService;
import org.springframework.stereotype.Component;

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
@Service(interfaceClass = TransferService.class,group = "bankCardTransfer")
@Component
public class BankCardTransferServiceImpl implements TransferService {
    @Override
    public TransactionRecordDo transfer(TransactionRecordDo transactionRecord) {
        return null;
    }
}
