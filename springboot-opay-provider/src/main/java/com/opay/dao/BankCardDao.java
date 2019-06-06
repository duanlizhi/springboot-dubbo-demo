package com.opay.dao;

import com.opay.entity.BankCardDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.math.BigInteger;
import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 10:46</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface BankCardDao {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 10:57 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param bankCard 银行卡数据对象
     * @return bigInteger 数据库自增主键值
     * @throws DataAccessException 数据访问异常
     */
    Long saveAndReturnId(BankCardDo bankCard) throws DataAccessException;
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 10:58 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param bankCard 银行卡数据对象
     * @return void 无返回值
     * @throws DataAccessException 数据访问异常
     */
    void updateById(BankCardDo bankCard) throws DataAccessException;

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据用户的账户id和身份证号来查询银行卡列表</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 11:01 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param accountId 账户id
     * @param idCard 自然人身份证号
     * @return java.util.List<com.opay.entity.BankCardDo>
     * @throws DataAccessException
     */
    List<BankCardDo> listBankCard(@Param("accountId") Long accountId, @Param("idCard") String idCard)
            throws DataAccessException;

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据银行卡相关信息查询银行卡列表</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 13:59 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param bankCard
     * @return java.util.List<com.opay.entity.BankCardDo>
     * @throws DataAccessException
     */
    List<BankCardDo> listBankCardByCardInfo(BankCardDo bankCard) throws DataAccessException;

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>删除某个用户绑定的银行卡</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 16:59 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param bankCardId
     * @return void
     * @throws DataAccessException
     */
    void deleteBankCard(@Param("bankCardId") Long bankCardId) throws DataAccessException;
}
