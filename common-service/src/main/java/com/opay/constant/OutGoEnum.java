package com.opay.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>收入支出类型枚举类</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-11 13:49</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum  OutGoEnum {
    /**
     * 收入
     */
    OUT("OUT","支出"),
    INT("INT","收入");
    private String value;
    private String name;
}
