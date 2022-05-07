package com.recipe.mall.service.impl;

import com.recipe.mall.dao.UserMapper;
import com.recipe.mall.enums.RoleEnum;
import com.recipe.mall.pojo.User;
import com.recipe.mall.service.IUserService;
import com.recipe.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.recipe.mall.enums.ResponseEnum.*;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo<User> register(User user) {
        //username 不重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0){
            return ResponseVo.error(USERNAME_EXIST);
        }

        //email 不重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0){
            return ResponseVo.error(EMAIL_EXIST);
        }

        //ROLE
        user.setRole(RoleEnum.CUSTOMER.getCode());

        //MD5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        //写入
        int res = userMapper.insertSelective(user);
        if(res == 0){
            return ResponseVo.error(ERROR);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            //用户不存在（返回：用户名或密码错误 ）
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(返回：用户名或密码错误 )
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }

        return ResponseVo.success(user);
    }

    @Override
    public ResponseVo<User> updatePwd(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            //用户不存在（返回：用户名或密码错误 ）
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }
        int check = userMapper.changePassword(username, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<List<User>> selectAll() {
        List<User> users = userMapper.selectAll();


        return ResponseVo.success(users);
    }
}
