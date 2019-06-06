package com.opay.service;

import com.opay.entity.AccountDo;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>账户操作service接口定义</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 10:52</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface AccountService {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>测试dubbo 保存账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 10:58 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param name 名称
     * @return boolean
     */
    Boolean saveAccount(String name);
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>保存账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 14:43 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountDo 账户数据对象
     * @return boolean
     */
    Boolean save(AccountDo accountDo);
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>修改账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 14:43 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountDo 账户数据对象
     * @return boolean
     */
    Boolean update(AccountDo accountDo);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据账户的id、身份证号查询账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 13:14 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountId 账户id
     * @param idCard 账户身份证号
     * @return com.opay.entity.AccountDo
     */
    AccountDo getAccountByIdOrIdCard(Long accountId, String idCard);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 13:36 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountId 账户id
     * @param idCard 账户身份证号码
     * @param name 账号名称
     * @return java.util.List<com.opay.entity.AccountDo>
     * @throws
     */
    List<AccountDo> listAccountByIdOrIdCardOrName(Long accountId, String idCard, String name);
}
