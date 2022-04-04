package com.recipe.mall.controller;

import com.recipe.mall.consts.MallConst;
import com.recipe.mall.form.UserLoginForm;
import com.recipe.mall.form.UserRegisterForm;
import com.recipe.mall.pojo.User;
import com.recipe.mall.service.IUserService;
import com.recipe.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.recipe.mall.enums.ResponseEnum.PARAM_ERROR;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userForm,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }

        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session){
        if(bindingResult.hasErrors()){
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());

        //session
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());

        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session){
        session.removeAttribute(MallConst.CURRENT_USER);

        return ResponseVo.success();
    }
}
