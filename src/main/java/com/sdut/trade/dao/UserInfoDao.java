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
     * 查看是否的存在某用户
     * @param userInfo
     * @return
     */
    boolean hasUser(UserInfo userInfo);
}
