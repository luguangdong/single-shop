package com.luxiu.single.shop.web.admin.web.controller;

import com.luxiu.single.shop.commons.constant.ConstantUtils;
import com.luxiu.single.shop.commons.utils.CookieUtils;
import com.luxiu.single.shop.domain.TbUser;
import com.luxiu.single.shop.domain.vo.UserVo;
import com.luxiu.single.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private TbUserService tbUserService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam(value = "isRemember",required = false) String isRemember, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model){
        boolean isRemeber = isRemember == null ? false : true;
        //没有选择记住我,将之前的cookie信息删除
        if(!isRemeber){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"userinfo");
        }

        TbUser tbUser = tbUserService.login(email, password);
        if(tbUser == null){
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login(httpServletRequest,httpServletResponse);
        }else {
            //选择记住我,将用户信息放入cookie中
            if(isRemeber){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,"userinfo",String.format("%s:%s",email,password),7 * 24 * 60 * 60);
            }
            //将登录信息放入会话,
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        String userinfo = CookieUtils.getCookieValue(httpServletRequest, "userinfo");
        if(!StringUtils.isBlank(userinfo)){
            String[] userinfoArray = userinfo.split(":");
            String email = userinfoArray[0];
            String password = userinfoArray[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemeber",true);
        }


        return "login";
    }
}
