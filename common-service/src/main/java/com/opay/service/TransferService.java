package com.opay.service;

import com.opay.entity.TransactionRecordDo;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>转账交易接口定义</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 17:48</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public interface TransferService {
    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>转账操作接口</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-06 17:52 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param transactionRecord 转账记录数据对象
     * @return com.opay.entity.TransactionRecordDo
     * @throws
     */
    TransactionRecordDo transfer(TransactionRecordDo transactionRecord);
}
