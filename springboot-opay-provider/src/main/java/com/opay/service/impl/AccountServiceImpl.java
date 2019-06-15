package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.constant.ErrorEnum;
import com.opay.dao.AccountDao;
import com.opay.entity.AccountDo;
import com.opay.exception.CustomerException;
import com.opay.service.AccountService;
import com.opay.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

@Service(interfaceClass = AccountService.class, token = "true")
@Component
public class AccountServiceImpl implements AccountService {
    /**
     * 服务降级：mock
     * token: true
     * 负载均衡
     * loadbalance: roundrobin
     * 内置负载均衡算法：random：随机算法，默认；roundrobin：轮询算法。按照权重，在服务端指定；
     * leastactive：最少活跃度算法；consistenthash：一致性hash算法
     * 权重：
     * weight: 2
     * 直接限流：
     * executes：每个方法的并发执行数
     * accepts: 最多接受的连接数
     * actives: 并发执行数，提供者（并发执行数）和消费者（每个方法发出的连接数）均可
     * connections: 并发连接数，提供者（并发连接）和消费者均可
     * 还有间接限流等等
     * 声明式缓存：在消费者端设置cache=true，默认缓存1000
     */
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerException save(AccountDo accountDo) {
        try {
            ValidatorUtil.validate(accountDo);
            //验证该身份证号用户是否已经在用户中存在
            AccountDo accountByIdOrIdCard = this.getAccountByIdOrIdCard(null, accountDo.getIdCard());
            if(null != accountByIdOrIdCard) {
                return CustomerException.builder().code(ErrorEnum.ID_CARD_EXIT.getCode())
                        .msg(ErrorEnum.ID_CARD_EXIT.getMsg()).build();
            }
            if(null == accountDo.getBalance()) {
                accountDo.setBalance(new BigDecimal(0));
            }
            accountDao.save(accountDo);

        } catch (CustomerException e) {
            logger.error("保存账号信息：name:{},id_card:{},mobile_number: {}\r err: {}",accountDo.getName(),
                    accountDo.getIdCard(),accountDo.getMobileNumber(),e.getMsg());
            return CustomerException.builder().code(ErrorEnum.PARAMS_NOT_NULL.getCode())
                    .msg(e.getMsg()).build();
        }
        return CustomerException.builder().code(ErrorEnum.SUCCESS.getCode())
                .msg(ErrorEnum.SUCCESS.getMsg()).data(accountDo).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerException update(AccountDo accountDo) {
        try {
            AccountDo accountByIdOrIdCard = this.getAccountByIdOrIdCard(accountDo.getId(), null);
            if(null == accountByIdOrIdCard) {
               return CustomerException.builder().code(ErrorEnum.ACCOUNT_NOT_EXIT.getCode())
               .msg(ErrorEnum.ACCOUNT_NOT_EXIT.getMsg()).build();
            }
            accountDao.updateById(accountDo);
        } catch (DataAccessException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("更新账号信息：name:{},id_card:{},mobile_number: {}\r err: {}",accountDo.getName(),
                        accountDo.getIdCard(),accountDo.getMobileNumber(),e.getMessage());
            }
            return CustomerException.builder().code(ErrorEnum.FAILED.getCode())
                    .msg(ErrorEnum.FAILED.getMsg()).build();
        }
        return CustomerException.builder().code(ErrorEnum.SUCCESS.getCode())
                .msg(ErrorEnum.SUCCESS.getMsg()).build();
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
    public boolean decrease(long fromAccountId, BigDecimal amount) {
        boolean flag = false;
        AccountDo accountDo = new AccountDo();
        accountDo.setId(fromAccountId);
        accountDo.setBalance(amount);
        try {
            accountDao.updateById(accountDo);
            flag = true;
        } catch (DataAccessException e) {
            logger.info("扣减账户余额失败，fromAccountId：{}, amount: {}",fromAccountId,amount);
        }
        return flag;
    }

    @Override
    public boolean increase(long toAccountId, BigDecimal amount) {
        boolean flag = false;
        AccountDo accountDo = new AccountDo();
        accountDo.setId(toAccountId);
        accountDo.setBalance(amount);
        try {
            accountDao.updateById(accountDo);
            flag = true;
        } catch (DataAccessException e) {
            logger.info("增加账户余额失败，fromAccountId：{}, amount: {}",toAccountId,amount);
        }
        return flag;
    }

    @Override
    public Boolean deleteAll() {
        return accountDao.deleteAll();
    }

    @Override
    public Boolean saveAccount(String name) {
        System.out.println("服务调用>>> :" + name);
        return false;
    }


}
