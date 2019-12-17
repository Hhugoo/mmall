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
public class OrderItem {

    // 主键id
    private Integer id;

    // 用户id
    private Integer userId;

    // 订单id 与订单关联
    private Long orderNo;

    // 商品id
    private Integer productId;

    // 商品名字
    private String productName;

    // 主图 用于显示
    private String productImage;

    // 实际支付时的单价
    private BigDecimal currentUnitPrice;

    // 数量
    private Integer quantity;

    // 这个Item的总价
    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;

}