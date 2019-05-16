package com.mmall.dao;

import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> selectByNameAndProductId(@Param("productName")String productName, @Param("productId")Integer productId);

    List<Product> selectByNameAndCategoryIds(@Param("productName")String productName, @Param("categoryIdList")List<Integer> categoryIdList);

    // 这里要用Integer，因为可能商品被删除，这时候返回null，int不能为null
    Integer selectStockByProductId(Integer id);

}