package com.opay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName("bank_card")
public class BankCardDo implements Serializable {
    private static final long serialVersionUID = -9055213091615324518L;
    /**
     * 主键id
     */
    @TableId
    private BigInteger id;
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String cardNumber;
    /**
     * 账户id
     */
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
