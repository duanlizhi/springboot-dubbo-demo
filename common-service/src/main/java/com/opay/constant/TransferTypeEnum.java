package com.opay.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 17:56</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum TransferTypeEnum {
    /**
     * 充值
     */
    DEPOSIT(1,"充值"),
    TRANSFER(2,"转账");
    private int num;
    private String name;
}
