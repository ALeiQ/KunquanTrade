package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.UserInfoDao;
import com.sdut.trade.entity.UserInfo;
import com.sdut.trade.entity.UserInfoExample;
import com.sdut.trade.mapper.UserInfoMapper;

/**
 * 类描述：用户信息接口实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Component
public class UserInfoDaoImp implements UserInfoDao {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 通过用户帐号获取用户
     *
     * @param name
     *
     * @return
     */
    @Override
    public UserInfo getByName(String name) {

        UserInfoExample userInfoExample = new UserInfoExample();

        userInfoExample.createCriteria()
                .andNameEqualTo(name);

        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);

        return userInfoList.size() == 0 ? null : userInfoList.get(0);
    }

    /**
     * 通过Id获取用户信息
     *
     * @param userId
     *
     * @return
     */
    @Override
    public UserInfo getById(int userId) {

        return userInfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 更新用户信息
     *
     * @param userInDB
     *
     * @return
     */
    @Override
    public int updateUser(UserInfo userInDB) {
        return userInfoMapper.updateByPrimaryKey(userInDB);
    }
}
