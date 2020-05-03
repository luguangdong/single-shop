package com.luxiu.single.shop.web.admin.dao;

import com.luxiu.single.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName TbUserMapper
 * @date 2020/5/2 21:32
 * @company https://www.singlewindow.cn/
 */
public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    TbUser findByEmail(@Param("email") String email);

    List<TbUser> selectAll();
}