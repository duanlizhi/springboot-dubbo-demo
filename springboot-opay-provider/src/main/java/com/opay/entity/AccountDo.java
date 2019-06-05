package com.opay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@TableName("account")
public class AccountDo implements Serializable {
    private static final long serialVersionUID = 6358112934600153550L;
    /**
     * 主键id
     */
    @TableId
    private BigInteger id;
    /**
     * 自然人名称
     */
    @NotNull(message = "自然人名称不能为空")
    private String name;
    /**
     * 身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",message = "身份证格式不合法")
    @NotNull(message = "身份证号不能为空")
    private String idCard;
    /**
     * 自然人昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    @Length(min = 11,max = 11,message = "手机号格式不正确，长度须为11位")
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
