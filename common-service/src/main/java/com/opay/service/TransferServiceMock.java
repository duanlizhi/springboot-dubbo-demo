package com.opay.service;

import com.opay.constant.ErrorEnum;
import com.opay.entity.TransferDTO;
import com.opay.exception.CustomerException;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-14 16:09</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public class TransferServiceMock implements TransferService {
    public CustomerException transfer(TransferDTO transferDTO) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }
}
