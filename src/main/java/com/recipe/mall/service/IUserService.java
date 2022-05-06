package com.recipe.mall.service;

import com.recipe.mall.pojo.User;
import com.recipe.mall.vo.ResponseVo;

public interface IUserService {
    /**
     * 注册
     */
    ResponseVo<User> register(User user);


    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);

    ResponseVo<User> updatePwd(String username, String password);
}
