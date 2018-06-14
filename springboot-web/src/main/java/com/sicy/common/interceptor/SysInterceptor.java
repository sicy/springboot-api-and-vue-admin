package com.sicy.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sicy.common.ReturnMsg;
import com.sicy.common.controller.BaseController;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (填写类功能描述)
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <br/>
 *          <br/>修订人		修订时间			描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy		2018/5/9		初始创建
 */
@Component
public class SysInterceptor implements HandlerInterceptor {

    /**
     * 利用正则映射到不需要拦截的路径
     */
    @Value("${interceptor.exURL}")
    private String[] mappingURL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //为RequestParam类附加请求
        RequestUtils.setRequest(request);
        RequestUtils.setResponse(response);
        String url = request.getRequestURL().toString();
        //是否被拦截,需要过滤不拦截的URL数组
        boolean isBool = true;
        //判断配置的URL是否需要被拦截，如果不需要则直接返回true
        if (mappingURL != null && mappingURL.length > 0) {
            for (String urlStr : mappingURL) {
                if (url.contains(urlStr)) {
                    //过滤掉，不需要拦截
                    isBool = false;
                    break;
                }
            }
        }
        if(!isBool){
            //不需要拦截，直接执行控制器类
            return true;
        }

        //需要拦截的URL，判断其session是否过期，如果过期直接回到登陆页面
        User user = (User)request.getSession().getAttribute(User.SYS_LOGIN_INFO);
        if(null == user){
            ReturnMsg msg = new ReturnMsg(-1, false, "not login", null);
            BaseController.renderText(JSONObject.toJSONString(msg), response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
