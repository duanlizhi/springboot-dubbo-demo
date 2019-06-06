package com.opay.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerException extends RuntimeException {
    private String msg;
//    private int code = 500;
}
