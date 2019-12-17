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
public class Shipping {

    // 主键id
    private Integer id;

    // 用户id
    private Integer userId;

    // 收货姓名
    private String receiverName;

    // 收货固定电话
    private String receiverPhone;

    // 收货移动电话
    private String receiverMobile;

    // 收货省份
    private String receiverProvince;

    // 收货城市
    private String receiverCity;

    // 收货区/县
    private String receiverDistrict;

    // 详细收货地址
    private String receiverAddress;

    // 邮编
    private String receiverZip;

    private Date createTime;

    private Date updateTime;

}