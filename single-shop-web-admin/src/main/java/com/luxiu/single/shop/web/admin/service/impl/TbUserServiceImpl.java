package com.luxiu.single.shop.web.admin.service.impl;

import com.luxiu.single.shop.domain.TbUser;
import com.luxiu.single.shop.web.admin.dao.TbUserMapper;
import com.luxiu.single.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName TbUserServiceImpl
 * @date 2020/5/2 21:40
 * @company https://www.singlewindow.cn/
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    public List<TbUser> selectAll() {
        return tbUserMapper.selectAll();
    }

    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserMapper.findByEmail(email);
        if(tbUser != null){
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5Password.equals(tbUser.getPassword())){
                    return tbUser;
            }
        }
        return null;
    }
}
