package com.luxiu.single.shop.web.admin.tests;

import com.luxiu.single.shop.domain.TbUser;
import com.luxiu.single.shop.web.admin.dao.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName UserTests
 * @date 2020/5/2 21:33
 * @company https://www.singlewindow.cn/
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class UserTests {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void test1(){
        //TbUser tbUser = tbUserMapper.selectByPrimaryKey(37L);
        //TbUser byEmail = tbUserMapper.findByEmail("luxiu.@gamil.com");
        String p= "123456";
        String md5Password = DigestUtils.md5DigestAsHex(p.getBytes());
        System.out.println(md5Password);
    }
}
