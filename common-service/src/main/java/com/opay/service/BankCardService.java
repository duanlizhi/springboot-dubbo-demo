package com.opay.service;

import com.opay.entity.BankCardDo;
import com.opay.exception.CustomerException;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>银行卡相关服务</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 18:39</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface BankCardService {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用户绑定银行卡</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 18:41 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param bankCard 银行卡数据对象
     * @return com.opay.exception.CustomerException
     */
    CustomerException saveBindCard(BankCardDo bankCard);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>删除用户绑定的银行卡</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 19:43 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param cardId 银行卡卡片id
     * @param cardNumber 银行卡号
     * @return com.opay.exception.CustomerException
     */
    CustomerException deleteCard(Long cardId,String cardNumber);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>查找用户的银行卡列表</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 18:47 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountId 账户id
     * @param idCard 身份证号
     * @return java.util.List<com.opay.entity.BankCardDo>
     */
    List<BankCardDo> listBankCard(Long accountId,String idCard);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 13:55 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountId 账户id
     * @param cardNumber 银行卡卡号
     * @return com.opay.entity.BankCardDo
     * @throws
     */
    BankCardDo getBankCard(Long accountId,String cardNumber);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-14 14:09 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param
     * @return java.lang.Boolean
     * @throws
     */
    Boolean deleteAll();
}
