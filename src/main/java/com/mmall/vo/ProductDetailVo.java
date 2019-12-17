package com.mmall.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailVo {

    // 商品id
    private Integer id;

    // 分类id 对应category的主键
    private Integer categoryId;

    // 商品名字
    private String name;

    // 副标题
    private String subtitle;

    // 主图url
    private String mainImage;

    // 图片地址,json格式,扩展用
    private String subImages;

    // 产品细节
    private String detail;

    // 产品价格
    private BigDecimal price;

    // 产品库存
    private Integer stock;

    // 产品状态
    private Integer status;

    private String createTime;

    private String updateTime;

    // 图片地址 eg：127.0.0.1
    private String imageHost;

    // 父类型
    private Integer parentCategoryId;

}
