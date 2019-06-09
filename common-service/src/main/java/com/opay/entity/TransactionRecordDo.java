package com.opay.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>交易记录展示对象</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 14:05</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Data
public class TransactionRecordDo implements Serializable {
    private static final long serialVersionUID = 7288813630142969952L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 交易单号，每一笔交易客户端生成一个交易编号
     */
    @NotNull(message = "交易单号不能为空")
    private String orderNo;
    /**
     * 交易类型（1:充值 2:转账）
     */
    @NotNull(message = "交易类型不能为空")
    private Integer type;
    /**
     * 转账渠道（1:余额 2:银行卡）
     */
    @NotNull(message = "交易渠道不能为空")
    private Integer channel;
    /**
     * 当交易渠道为2时，标注使用的是哪张银行卡
     */
    private Long bankCardId;
    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    @Min(value = 0, message = "交易金额最小为0")
    private BigDecimal amount;
    /**
     * 交易记录状态(0:处理中，1: 成功，2:失败)
     */
    private Integer status;
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
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

}
