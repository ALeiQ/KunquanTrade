package com.sdut.trade.dao.impl;

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
     * 查看是否的存在某用户
     *
     * @param userInfo
     *
     * @return
     */
    @Override
    public boolean hasUser(UserInfo userInfo) {

        UserInfoExample userInfoExample = new UserInfoExample();

        userInfoExample.createCriteria()
                .andNameEqualTo(userInfo.getName())
                .andPasswordEqualTo(userInfo.getPassword());

        return userInfoMapper.countByExample(userInfoExample) == 1;
    }
}
