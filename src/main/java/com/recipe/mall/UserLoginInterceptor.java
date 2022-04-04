package com.recipe.mall;

import com.recipe.mall.consts.MallConst;
import com.recipe.mall.exception.UserLoginException;
import com.recipe.mall.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(user == null){
            throw new UserLoginException();
        }
        return true;
    }
}
