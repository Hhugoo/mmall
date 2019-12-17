package com.mmall.pojo;

import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    // 主键id
    private Integer id;

    // 父品类的id
    private Integer parentId;

    // 商品名
    private String name;

    // 商品的状态 上架/下架
    private Boolean status;

    // 排序 暂时未用
    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

}