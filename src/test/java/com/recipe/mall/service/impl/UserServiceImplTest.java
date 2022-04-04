package com.recipe.mall.service.impl;

import com.recipe.mall.MallApplicationTests;
import com.recipe.mall.enums.RoleEnum;
import com.recipe.mall.pojo.User;
import com.recipe.mall.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class UserServiceImplTest extends MallApplicationTests {

    @Autowired
    private IUserService userService;
    @Test
    void register() {
        User user = new User("jack","123456","jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
}
