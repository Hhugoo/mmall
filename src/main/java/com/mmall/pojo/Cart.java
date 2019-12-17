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
public class Cart {

    // 主键
    private Integer id;

    // 用户id
    private Integer userId;

    // 产品id
    private Integer productId;

    // 数量
    private Integer quantity;

    // 是否选中
    private Integer checked;

    private Date createTime;

    private Date updateTime;
}