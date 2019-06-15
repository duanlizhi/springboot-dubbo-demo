package com.opay.service;

import com.opay.constant.ErrorEnum;
import com.opay.entity.BankCardDo;
import com.opay.exception.CustomerException;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-14 16:06</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public class BankCardServiceMock implements  BankCardService {
    public CustomerException saveBindCard(BankCardDo bankCard) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }

    public CustomerException deleteCard(Long cardId, String cardNumber) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }

    public List<BankCardDo> listBankCard(Long accountId, String idCard) {
        return null;
    }

    public BankCardDo getBankCard(Long accountId, String cardNumber) {
        return null;
    }

    public Boolean deleteAll() {
        return false;
    }
}
