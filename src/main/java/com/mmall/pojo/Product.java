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
public class Product {

    // 主键id
    private Integer id;

    // 分类id,对应mmall_category表的主键
    private Integer categoryId;

    // 商品名字
    private String name;

    // 商品副标题
    private String subtitle;

    // 产品主图,url相对地址
    private String mainImage;

    // 图片地址,json格式,扩展用
    private String subImages;

    // 商品详情
    private String detail;

    // 价格,单位-元保留两位小数
    private BigDecimal price;

    // 库存
    private Integer stock;

    // 商品状态.1-在售 2-下架 3-删除
    private Integer status;

    private Date createTime;

    private Date updateTime;

}