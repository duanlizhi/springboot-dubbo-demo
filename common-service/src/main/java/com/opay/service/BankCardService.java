package com.opay.service;

import com.opay.entity.BankCardDo;

import java.math.BigInteger;
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
     * @return boolean
     */
    Boolean saveBindCard(BankCardDo bankCard);

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
     * @param cardId
     * @param cardNumber
     * @return java.lang.Boolean
     */
    Boolean deleteCard(BigInteger cardId,String cardNumber);

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
     * @param name 自然人姓名
     * @param idCard 身份证号
     * @return java.util.List<com.opay.entity.BankCardDo>
     */
    List<BankCardDo> listBankCard(BigInteger accountId,String name,String idCard);
}