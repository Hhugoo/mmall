package com.mmall.controller.backend;


import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest httpServletRequest, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息！");
//        }
//        String userString = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.stringToObj(userString, User.class);
//
//        if(user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
//        }
//        //校验是否是管理员
//        if(iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.addCategory(categoryName, parentId);
//        }else {
//            return ServerResponse.createByErrorMessage("非管理员登陆，无权限。");
//        }

        //登陆以及权限的判断通过拦截器判断
        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest httpServletRequest, Integer categoryId, String categoryName) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息！");
//        }
//        String userString = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.stringToObj(userString, User.class);
//
//        if(user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
//        }
//        //校验是否是管理员
//        if(iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.updateCategoryName(categoryId, categoryName);
//        }else {
//            return ServerResponse.createByErrorMessage("非管理员登陆，无权限。");
//        }
        //登陆以及权限的判断通过拦截器判断
        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallerlCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息！");
//        }
//        String userString = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.stringToObj(userString, User.class);
//
//        if(user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
//        }
//        //校验是否是管理员
//        if(iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        }else {
//            return ServerResponse.createByErrorMessage("非管理员登陆，无权限。");
//        }
        //登陆以及权限的判断通过拦截器判断
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息！");
//        }
//        String userString = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.stringToObj(userString, User.class);
//
//        if(user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
//        }
//        //校验是否是管理员
//        if(iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        }else {
//            return ServerResponse.createByErrorMessage("非管理员登陆，无权限。");
//        }
        //登陆以及权限的判断通过拦截器判断
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }

}
