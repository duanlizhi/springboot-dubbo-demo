package com.opay.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
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
    private BigInteger id;
    /**
     * 开户行名称
     */
    @NotNull(message = "开户行不能为空")
    private String bankName;
    /**
     * 银行卡号
     */
    @Pattern(regexp = "^([1-9]{1})(\\d{14}|\\d{18})$",message = "")
    @NotNull(message = "银行卡号不能为空")
    private String cardNumber;
    /**
     * 账户id
     */
    @NotNull(message = "账户id不能为空")
    private BigInteger accountId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
