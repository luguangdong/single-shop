package com.luxiu.single.shop.web.admin.service.impl;

import com.luxiu.single.shop.commons.dto.BaseResult;
import com.luxiu.single.shop.commons.utils.RegexpUtils;
import com.luxiu.single.shop.domain.TbUser;
import com.luxiu.single.shop.web.admin.dao.TbUserMapper;
import com.luxiu.single.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
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

    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
            // 新增用户
            if (tbUser.getId() == null) {
                // 密码需要加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserMapper.insert(tbUser);
            }

            // 编辑用户
            else {
                // 编辑用户时如果没有输入密码则沿用原来的密码
                if (StringUtils.isBlank(tbUser.getPassword())) {
                    TbUser oldTbUser = tbUserMapper.selectByPrimaryKey(tbUser.getId());
                    tbUser.setPassword(oldTbUser.getPassword());
                } else {
                    // 验证密码是否符合规范，密码长度介于 6 - 20 位之间
                    if (StringUtils.length(tbUser.getPassword()) < 6 || StringUtils.length(tbUser.getPassword()) > 20) {
                        return BaseResult.fail("密码长度必须介于 6 - 20 位之间");
                    }

                    // 设置密码加密
                    tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                }
                tbUserMapper.updateByPrimaryKeySelective(tbUser);
            }
            baseResult.setMessage("保存用户信息成功");
        }


        return baseResult;
    }

    public TbUser selectById(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }


    /**
     * 用户信息有效性验证
     *
     * @param tbUser
     * @return
     */
    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult result = BaseResult.success();
        if (StringUtils.isBlank(tbUser.getEmail())) {
            result = BaseResult.fail("邮箱不能为空，请重新输入");
        } else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
            result = BaseResult.fail("邮箱格式不正确，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPassword())) {
            result = BaseResult.fail("密码不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getUsername())) {
            result = BaseResult.fail("用户名不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            result = BaseResult.fail("手机不能为空，请重新输入");
        }else if(!RegexpUtils.checkPhone(tbUser.getPhone())){
            result = BaseResult.fail("手机格式不正确，请重新输入");
        }
        return result;

    }
}
