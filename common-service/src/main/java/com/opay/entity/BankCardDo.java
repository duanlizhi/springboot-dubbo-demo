package com.opay.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>银行卡数据对象</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 13:59</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Data
public class BankCardDo implements Serializable {
    private static final long serialVersionUID = -9055213091615324518L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 开户行名称
     */
    @NotNull(message = "开户行不能为空")
    private String bankName;
    /**
     * 银行卡号
     */
    @Pattern(regexp = "^([1-9]{1})(\\d{14}|\\d{18})$",message = "银行卡号不合法")
    @NotNull(message = "银行卡号不能为空")
    private String cardNumber;
    /**
     * 银行预留手机号
     */
    @Pattern(regexp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$",
            message = "手机号格式不正确")
    @Size(max = 11,min = 11, message = "手机号长度为11位")
    @NotNull(message = "银行预留手机号不能为空")
    private String mobileNumber;
    /**
     * 账户id
     */
    @NotNull(message = "账户id不能为空")
    private Long accountId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
