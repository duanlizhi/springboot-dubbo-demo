package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.dao.BankCardDao;
import com.opay.entity.AccountDo;
import com.opay.entity.BankCardDo;
import com.opay.exception.CustomerException;
import com.opay.service.AccountService;
import com.opay.service.BankCardService;
import com.opay.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
 * <dd>2019-06-06 10:30</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Service(interfaceClass = BankCardService.class)
@Component
public class BankCardServiceImpl implements BankCardService {
    private static final Logger logger = LoggerFactory.getLogger(BankCardService.class);

    @Resource
    private BankCardDao bankCardDao;
    @Autowired
    private AccountService accountService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBindCard(BankCardDo bankCard) {
        boolean flag = true;
        try {
            ValidatorUtil.validate(bankCard);
            //查询当前用户是否存在
            AccountDo accountByIdOrIdCard = accountService.getAccountByIdOrIdCard(bankCard.getAccountId(), null);
            if(null == accountByIdOrIdCard) {
                logger.info("用户绑定银行卡失败：accountId: {},cardNumber: {}, bankName: {}, errMsg: {}",
                        bankCard.getAccountId(),bankCard.getCardNumber(),bankCard.getBankName(),"未查询到账户信息");
                return false;
            }
            //查询当前用户是否绑定过当前卡片，如果绑定过不可重复绑定。
            //不同用户可以绑定同一张卡片
            BankCardDo bankCardGet = this.getBankCard(bankCard.getAccountId(), bankCard.getCardNumber());
            if(null != bankCardGet) {
                logger.info("用户绑定银行卡失败： accountId: {},cardNumber: {}, bankName: {}, errMsg: {}",
                        bankCard.getAccountId(),bankCard.getCardNumber(),bankCard.getBankName(),"该绑定的卡片已经绑定过了");
                return false;
            }
            bankCardDao.saveAndReturnId(bankCard);
            System.out.println("新增银行卡id： " + bankCard.getId());
        } catch (CustomerException e) {
            logger.info("新增银行卡失败：所属账户id：{},开户行：{},银行卡号：{}, errMsg: {}",bankCard.getAccountId(),
                    bankCard.getBankName(),bankCard.getCardNumber(), e.getMessage());
            flag = false;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCard(Long cardId, String cardNumber) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BankCardDo> listBankCard(Long accountId, String idCard) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public BankCardDo getBankCard(Long accountId, String cardNumber) {
        BankCardDo bankCard = new BankCardDo();
        bankCard.setAccountId(accountId);
        bankCard.setCardNumber(cardNumber);
        try {
            List<BankCardDo> bankCardDos = bankCardDao.listBankCardByCardInfo(bankCard);
            if(null != bankCardDos && bankCardDos.size() > 0) {
                return bankCardDos.get(0);
            }
        } catch (DataAccessException e) {
            logger.info("查询单张银行卡信息失败： accountId: {}, cardNumber: {}, errMsg: {}",
                    accountId,cardNumber,e.getMessage());
        }
        return null;
    }
}
