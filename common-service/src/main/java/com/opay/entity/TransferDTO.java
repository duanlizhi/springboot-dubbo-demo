package com.opay.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-09 20:47</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Data
public class TransferDTO {
    /**
     * 交易发起者账户id
     */
    @NotNull(message = "交易发起账户不能为空")
    private Long fromAccountId;
    /**
     * 当前交易转给账户id
     */
    @NotNull(message = "交易目的账户不能为空")
    private Long toAccountId;

    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    @Min(value = 0, message = "交易金额最小为0")
    private BigDecimal amount;
    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private Integer type;

    /**
     * 转账渠道（1:余额 2:银行卡）
     */
    @NotNull(message = "交易渠道不能为空")
    private Integer channel;
    /**
     * 交易单号，每一笔交易客户端生成一个交易编号
     */
    @NotNull(message = "交易单号不能为空")
    private String orderNo;
    /**
     * 银行卡号
     */
    private Long bankCardId;
}
