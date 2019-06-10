package com.opay.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>交易状态枚举类</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 17:26</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum TransferStatusEnum {
    /**
     * 交易处理中状态
     */
    PROCESSING(0, "处理中"),
    /**
     * 交易成功
     */
    SUCCESS(1, "成功"),
    /**
     * 交易失败
     */
    FAILED(2, "失败");

    private final int code;
    private final String value;
}