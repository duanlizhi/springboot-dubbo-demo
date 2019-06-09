package com.opay.dao;

import com.opay.entity.TransactionRecordDo;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>转账充值记录接口</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 14:14</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface TransactionRecordDao {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>保存交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 14:16 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecord 交易查询对象
     * @return void
     * @throws
     */
    void save(TransactionRecordDo transactionRecord);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据记录id和交易单号来更新交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 14:19 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecordDo 交易查询对象
     * @return void
     * @throws
     */
    void updateByIdOrOrderNo(TransactionRecordDo transactionRecordDo);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据交易单号、类型、转账渠道、发起用户来查询交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 14:24 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecord 交易查询对象
     * @return java.util.List<com.opay.entity.TransactionRecordDo>
     * @throws
     */
    List<TransactionRecordDo> listTransactionRecord(TransactionRecordDo transactionRecord);
}
