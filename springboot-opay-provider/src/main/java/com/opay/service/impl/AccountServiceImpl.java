package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.dao.AccountDao;
import com.opay.entity.AccountDo;
import com.opay.service.AccountService;
import com.opay.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 10:59</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Service(interfaceClass = AccountService.class)
@Component
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public Boolean save(AccountDo accountDo) {
        boolean flag = true;
        try {
            ValidatorUtil.validate(accountDo);
            accountDao.save(accountDo);

        } catch (Exception e) {
            flag = false;
            logger.error("保存账号信息：name:{},id_card:{},mobile_number: {}\r err: {}",accountDo.getName(),
                    accountDo.getIdCard(),accountDo.getMobileNumber(),e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean update(AccountDo accountDo) {
        boolean flag = true;
        try {
            accountDao.updateById(accountDo);
        } catch (DataAccessException e) {
            flag = false;
            if(logger.isDebugEnabled()) {
                logger.debug("更新账号信息：name:{},id_card:{},mobile_number: {}\r err: {}",accountDo.getName(),
                        accountDo.getIdCard(),accountDo.getMobileNumber(),e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public AccountDo getAccountByIdOrIdCard(Long accountId, String idCard) {
        AccountDo account = new AccountDo();
        account.setId(accountId);
        account.setIdCard(idCard);
        try {
            List<AccountDo> accountDos = accountDao.listAccountByIdOrIdCardOrName(account);
            if(null != accountDos && accountDos.size() > 0) {
                return accountDos.get(0);
            }
        } catch (DataAccessException e) {
            logger.error("获取单个账户信息失败，accountId: {},idCard: {},errMsg: {}",
                    accountId,idCard,e.getMessage());
        }
        return null;
    }

    @Override
    public List<AccountDo> listAccountByIdOrIdCardOrName(Long accountId, String idCard, String name) {
        AccountDo account = new AccountDo();
        account.setId(accountId);
        account.setIdCard(idCard);
        account.setName(name);
        try {
            List<AccountDo> accountDos = accountDao.listAccountByIdOrIdCardOrName(account);
            return accountDos;
        } catch (DataAccessException e) {
            logger.error("获取账户列表信息失败，accountId: {},idCard: {}, name: {}, errMsg: {}",
                    accountId,idCard,name,e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean saveAccount(String name) {
        System.out.println("服务调用>>> :" + name);
        return false;
    }
}
