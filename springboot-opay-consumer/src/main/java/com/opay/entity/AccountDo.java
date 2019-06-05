package com.opay.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>账户数据对象</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 13:44</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Data
public class AccountDo implements Serializable {
    private static final long serialVersionUID = 6358112934600153550L;
    /**
     * 主键id
     */
    private BigInteger id;
    /**
     * 自然人名称
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 自然人昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

}
