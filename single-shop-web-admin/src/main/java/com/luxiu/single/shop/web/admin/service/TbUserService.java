package com.luxiu.single.shop.web.admin.service;

import com.luxiu.single.shop.commons.dto.BaseResult;
import com.luxiu.single.shop.domain.TbUser;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName TbUserService
 * @date 2020/5/2 21:39
 * @company https://www.singlewindow.cn/
 */
public interface TbUserService {

    /**
     * 查询全部
     * @return
     */
    List<TbUser> selectAll();

    TbUser login(String email,String password);

    BaseResult save(TbUser tbUser);

    TbUser selectById(Long id);
}
