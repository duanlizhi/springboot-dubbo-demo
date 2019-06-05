package com.opay.service;

import com.opay.entity.AccountDo;

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
     * <dd>保存账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 10:58 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param name
     * @return boolean
     */
    boolean saveAccount(String name);
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
    boolean save(AccountDo accountDo);
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
    boolean update(AccountDo accountDo);
}
