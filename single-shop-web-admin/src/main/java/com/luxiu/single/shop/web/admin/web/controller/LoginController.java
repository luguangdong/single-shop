package com.luxiu.single.shop.web.admin.web.controller;

import com.alibaba.druid.support.http.util.IPAddress;
import com.luxiu.single.shop.commons.constant.ConstantUtils;
import com.luxiu.single.shop.commons.utils.CookieUtils;
import com.luxiu.single.shop.domain.TbUser;
import com.luxiu.single.shop.domain.vo.UserVo;
import com.luxiu.single.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName LoginController
 * @date 2020/5/2 20:49
 * @company https://www.singlewindow.cn/
 */
@Controller
@RequestMapping
public class LoginController {
    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TbUserService tbUserService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam(value = "isRemember",required = false) String isRemember, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model){
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            String hostName = localHost.getHostName();
            logger.info("===>Nginx负载均衡,当前访问的服务器:hostAddress={},hostName={}",hostAddress,hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        boolean isRemeber = isRemember == null ? false : true;
        logger.info("===>LoginController.login() POST方法前端传递的参数:email={},password={},isRemember={}",email,password,isRemeber);
        //没有选择记住我,将之前的cookie信息删除
        if(!isRemeber){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"userinfo");
            logger.info("===>LoginController.login() POST方法中没有选择记住我");
        }

        TbUser tbUser = tbUserService.login(email, password);
        if(tbUser == null){
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            login(httpServletRequest);
        }else {
            //选择记住我,将用户信息放入cookie中
            if(isRemeber){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,ConstantUtils.COOKIE_USER,String.format("%s:%s",email,password),7 * 24 * 60 * 60);
                logger.info("===>LoginController.login() POST方法中选择记住我,cookie信息为: cookieName={},cookieValue={}:{}",ConstantUtils.COOKIE_USER,email,password);
            }
            //将登录信息放入会话,
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
        return "login";
    }

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        String userinfo = CookieUtils.getCookieValue(httpServletRequest, ConstantUtils.COOKIE_USER);
        if(!StringUtils.isBlank(userinfo)){
            String[] userinfoArray = userinfo.split(":");
            String email = userinfoArray[0];
            String password = userinfoArray[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemeber",true);
            logger.info("===>LoginController.login() GET方法中获取前端页面cookie信息为: cookieName={},cookieValue={}:{}",ConstantUtils.COOKIE_USER,email,password);
        }


        return "login";
    }
}
