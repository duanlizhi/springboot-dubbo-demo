package com.opay.service;

import com.opay.entity.TransactionRecordDo;
import com.opay.exception.CustomerException;

import java.util.List;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>交易记录service接口</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 16:30</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface TransactionRecordService {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>保存交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 16:31 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecordDo 查询交易记录数据对象
     * @return boolean
     * @throws
     */
    CustomerException save(TransactionRecordDo transactionRecordDo);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>通过交易记录id或者交易号修改交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 16:33 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecordDo 查询交易记录数据对象
     * @return java.lang.Boolean
     * @throws
     */
    Boolean updateByIdOrOrderNo(TransactionRecordDo transactionRecordDo);

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 16:37 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecordDo 查询交易记录数据对象
     * @return com.opay.entity.TransactionRecordDo
     * @throws
     */
    TransactionRecordDo getTransactionRecord(TransactionRecordDo transactionRecordDo);


    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>根据id、交易单号或者交易发起人查询交易记录</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-09 16:34 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecordDo 查询交易记录数据对象
     * @return java.util.List<com.opay.entity.TransactionRecordDo>
     * @throws
     */
    List<TransactionRecordDo> listTransactionRecord(TransactionRecordDo transactionRecordDo);
}
