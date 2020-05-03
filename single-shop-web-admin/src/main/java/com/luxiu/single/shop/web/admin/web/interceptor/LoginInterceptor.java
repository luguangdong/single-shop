package com.luxiu.single.shop.web.admin.web.interceptor;

import com.luxiu.single.shop.commons.constant.ConstantUtils;
import com.luxiu.single.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Description: 登录拦截器
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName LoginInterceptor
 * @date 2020/4/30 17:46
 * @company https://www.singlewindow.cn/
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        // 未登录
        if (user == null) {
            httpServletResponse.sendRedirect("/login");
        }

        // 放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
