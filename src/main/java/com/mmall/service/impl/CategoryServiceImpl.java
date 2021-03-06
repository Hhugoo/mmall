package com.mmall.service.impl;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("iCategoryService")
@Slf4j
public class CategoryServiceImpl implements ICategoryService{


    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("创建产品参数错误");
        }
        Category category = Category.builder()
                .name(categoryName)
                .parentId(parentId)
                .status(true)
                .build();

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if(categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新产品名字出错");
        }
        Category category = Category.builder()
                .id(categoryId)
                .name(categoryName)
                .build();

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0) {
            return ServerResponse.createBySuccess("更新产品名字成功");
        }
        return ServerResponse.createByErrorMessage("更新产品名字失败");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectChildrenCategoryByParentId(categoryId);
        if(CollectionUtils.isEmpty(categoryList)) {
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList  = Optional.of(categorySet)
                .map(list -> list.stream()
                        .map(item -> item
                                .getId())
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
        return ServerResponse.createBySuccess(categoryIdList);
    }

    public Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        //将父节点放入set
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null) {
            categorySet.add(category);
        }

        //将子节点放入set
        List<Category> categoryList = categoryMapper.selectChildrenCategoryByParentId(categoryId);
        for(Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }
    public ServerResponse getParentId(Integer categoryId) {
        Integer parentId = categoryMapper.selectParentId(categoryId);
        return ServerResponse.createBySuccess(parentId);
    }
    }
