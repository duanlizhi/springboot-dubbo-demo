package com.opay.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>交易操作渠道</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 21:07</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum TransferChannelEnum {
    /**
     * 余额
     */
    BALANCE_CHANNEL(1,"余额"),
    BANKCARD_CHANNEL(2,"银行卡");
    private final int value;
    private final String name;
}
