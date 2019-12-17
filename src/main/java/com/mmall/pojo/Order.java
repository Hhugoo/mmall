package com.mmall.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    // 主键id
    private Integer id;

    // 订单号
    private Long orderNo;

    // 用户id
    private Integer userId;

    // 收货地址id
    private Integer shippingId;

    // 账单金额
    private BigDecimal payment;

    // 支付类型
    private Integer paymentType;

    // 邮费
    private Integer postage;

    // 订单状态
    private Integer status;

    // 支付时间
    private Date paymentTime;

    // 发货时间
    private Date sendTime;

    // 订单完成时间
    private Date endTime;

    // 订单关闭时间
    private Date closeTime;

    private Date createTime;

    private Date updateTime;

}