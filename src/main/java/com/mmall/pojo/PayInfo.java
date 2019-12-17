package com.mmall.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayInfo {

    // 主键id
    private Integer id;

    // 用户id
    private Integer userId;

    // 订单id
    private Long orderNo;

    // 支付平台
    private Integer payPlatform;

    // 支付流水号
    private String platformNumber;

    // 支付状态
    private String platformStatus;

    private Date createTime;

    private Date updateTime;

}