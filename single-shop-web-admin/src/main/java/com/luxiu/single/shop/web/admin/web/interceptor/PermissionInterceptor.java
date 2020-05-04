package com.luxiu.single.shop.web.admin.web.interceptor;

import com.luxiu.single.shop.commons.constant.ConstantUtils;
import com.luxiu.single.shop.commons.utils.CookieUtils;
import com.luxiu.single.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Description: 权限拦截器
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName PermissionInterceptor
 * @date 2020/4/30 17:46
 * @company https://www.singlewindow.cn/
 */
public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 以 login 结尾的请求
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("login")) {
           //判断session
            /* TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (user != null) {
                httpServletResponse.sendRedirect("/main");
            }*/
            //判断cookie
            String userinfo = CookieUtils.getCookieValue(httpServletRequest, "userinfo");
            if (!StringUtils.isBlank(userinfo)) {
                httpServletResponse.sendRedirect("/login");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
