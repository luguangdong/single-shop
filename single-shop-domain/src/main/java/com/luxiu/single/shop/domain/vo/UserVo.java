package com.luxiu.single.shop.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName TbUser
 * @date 2020/5/2 21:32
 * @company https://www.singlewindow.cn/
 */
@Data
public class UserVo implements Serializable {
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码，加密存储
    */
    private String password;

    /**
    * 注册手机号
    */
    private String phone;

    /**
    * 注册邮箱
    */
    private String email;

    private Date created;

    private Date updated;

    private Boolean isRemember;

    private static final long serialVersionUID = 1L;
}