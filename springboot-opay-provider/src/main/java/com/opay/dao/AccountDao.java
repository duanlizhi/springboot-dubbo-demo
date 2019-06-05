package com.opay.dao;

import com.opay.entity.AccountDo;
import org.springframework.dao.DataAccessException;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>账户信息交互dao接口</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 14:49</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface AccountDao {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>保存账户信息接口</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 14:51 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountDo 账户数据对象
     * @throws DataAccessException
     * @return void
     */
    void save(AccountDo accountDo) throws DataAccessException;
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>修改账户信息</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 14:52 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountDo 账户数据对象
     * @throws DataAccessException
     * @return void
     */
    void updateById(AccountDo accountDo) throws DataAccessException;
}
