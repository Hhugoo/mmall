package com.mmall.controller.common.interceptor;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        //要得到拦截的方法名
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        //解析参数，想要得到key-value
        StringBuffer requestParamsBuffer = new StringBuffer();
        Map paramMap = request.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String mapKey = (String)entry.getKey();
            String mapValue = StringUtils.EMPTY; //""

            //request参数中的Map中的value是String[]
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[])obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamsBuffer.append(mapKey).append("=").append(mapValue);
        }

//        if (StringUtils.equals(className, "UserManageController") && StringUtils.equals(methodName, "login")) {
//            log.info("权限拦截器拦截到请求，className:{}, methodName:{}", className, methodName);
//            //拦截到登陆请求，不打印在日志，因为危险
//            return true;
//        }
        log.info("权限拦截器拦截到请求,className:{},methodName:{},param:{}", className, methodName, requestParamsBuffer.toString());

        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userString = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.stringToObj(userString, User.class);
        }

        if (user == null || (user.getRole().intValue() != Const.Role.ROLE_ADMIN)) {
            // 返回false，即不会调用Co ntroller里的方法
            response.reset(); // !!! 这里要重置response，否则会报异常 getWrite() has already been called for this response
            response.setCharacterEncoding("UTF-8"); // 这里设置编码方式 不然会乱码
            response.setContentType("application/json;charset=UTF-8"); // 这里设置返回格式为json

            PrintWriter out = response.getWriter();

            //上传由于富文本的控件要求，要特殊处理返回值，这里区分是否登陆以及是否有权限
            if (user == null) {
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "请登陆管理员");
                    out.print(JsonUtil.objToString(resultMap));
                } else {
                    out.print(JsonUtil.objToString(ServerResponse.createByErrorMessage("拦截器拦截，请登陆管理员")));
                }
            } else {
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "无权限操作");
                    out.print(JsonUtil.objToString(resultMap));
                } else {
                    out.print(JsonUtil.objToString(ServerResponse.createByErrorMessage("拦截器拦截，无权限操作")));
                }
            }
            out.flush();
            out.close();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}