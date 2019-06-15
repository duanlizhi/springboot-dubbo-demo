package com.opay.service;

import com.opay.constant.ErrorEnum;
import com.opay.entity.AccountDo;
import com.opay.exception.CustomerException;

import java.math.BigDecimal;
import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-14 16:05</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public class AccountServiceMock implements AccountService {
    public Boolean saveAccount(String name) {
        return false;
    }

    public CustomerException save(AccountDo accountDo) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }

    public CustomerException update(AccountDo accountDo) {
        return CustomerException.builder().code(ErrorEnum.FAILED.getCode()).msg(ErrorEnum.FAILED.getMsg()).build();
    }

    public AccountDo getAccountByIdOrIdCard(Long accountId, String idCard) {
        return null;
    }

    public List<AccountDo> listAccountByIdOrIdCardOrName(Long accountId, String idCard, String name) {
        return null;
    }

    public boolean decrease(long fromAccountId, BigDecimal amount) {
        return false;
    }

    public boolean increase(long toAccountId, BigDecimal amount) {
        return false;
    }

    public Boolean deleteAll() {
        return null;
    }
}
