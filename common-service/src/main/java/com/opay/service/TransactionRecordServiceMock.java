package com.opay.service;

import com.opay.constant.ErrorEnum;
import com.opay.entity.TransactionRecordDo;
import com.opay.exception.CustomerException;
import org.omg.IOP.TransactionService;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-14 16:07</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public class TransactionRecordServiceMock implements TransactionRecordService {
    public CustomerException save(TransactionRecordDo transactionRecordDo) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }

    public Boolean updateByIdOrOrderNo(TransactionRecordDo transactionRecordDo) {
        return null;
    }

    public TransactionRecordDo getTransactionRecord(TransactionRecordDo transactionRecordDo) {
        return null;
    }

    public List<TransactionRecordDo> listTransactionRecord(TransactionRecordDo transactionRecordDo) {
        return null;
    }

    public Boolean deleteAll() {
        return null;
    }
}
