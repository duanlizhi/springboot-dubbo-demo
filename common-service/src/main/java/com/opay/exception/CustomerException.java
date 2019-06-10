package com.opay.exception;

import lombok.*;

import java.io.Serializable;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>自定义异常类</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-06 15:50</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Builder
@ToString
@Getter
public class CustomerException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2203651793109061095L;
    private String msg;
    private int code;
}
