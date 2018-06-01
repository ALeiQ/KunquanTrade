package com.sdut.trade.dao;

import com.sdut.trade.entity.UserInfo;

/**
 * 类描述：用户信息Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
public interface UserInfoDao {

    /**
     * 通过用户帐号获取用户
     *
     * @return
     */
    UserInfo getByName(String name);

    /**
     * 通过Id获取用户信息
     *
     * @param userId
     *
     * @return
     */
    UserInfo getById(int userId);

    /**
     * 更新用户信息
     *
     * @param userInDB
     *
     * @return
     */
    int updateUser(UserInfo userInDB);
}
