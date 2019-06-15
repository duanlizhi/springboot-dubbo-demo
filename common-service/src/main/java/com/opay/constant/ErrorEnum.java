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
 * <dd>2019-06-09 17:34</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /**
     * 请求成功
     */
    SUCCESS(0,"请求成功"),
    FAILED(-1,"请求失败"),
    /**
     * 余额不足
     */
    BALANCE_NOT_ENOUGH(10001,"余额不足"),
    /**
     * 账户不存在
     */
    ACCOUNT_NOT_EXIT(10002,"账户不存在"),
    PARAMS_NOT_NULL(10003,"参数校验失败"),
    REPEAT_COMMIT(10004,"交易订单重复提交"),
    ORDER_NO_QUERY(10005,"为查询到订单信息"),
    BANKCARD_REPEAT(10006,"银行卡信息重复绑定"),
    ID_CARD_EXIT(10007,"该身份证号已经存在");
    private final int code;
    private final String msg;

}
