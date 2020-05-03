package com.luxiu.single.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName TestController
 * @date 2020/4/30 17:54
 * @company https://www.singlewindow.cn/
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testController(){
        return "hello";
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ResponseBody
    public String testController2(){
        return "hello1";
    }
}
