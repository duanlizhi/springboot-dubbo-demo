package com.opay.utils;

import com.opay.exception.CustomerException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>使用valid进行参数校验</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 18:01</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
public class ValidatorUtil {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     *
     * <dl>
     * <dt><span class="strong">方法说明:</span></dt>
     * <dd>用一句话描述该方法的作用</dd>
     * </dl>
     * <dl><dt><span class="strong">创建时间:</span></dt>
     * <dd> 2019-06-05 18:02 </dd></dl>
     * <dl><dt><span class="strong">author:</span></dt>
     * <dd> duan_lizhi</dd></dl>
     * </dl>
     * @param object 待校验的对象
     * @param groups 待校验的组（可定义组接口）
     * @return void
     */
    public static void validate(Object object, Class<?>... groups)
            throws CustomerException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            System.out.println("validate: " + constraint.getMessage());
            throw CustomerException.builder().msg(constraint.getMessage()).build();
        }
    }
}
