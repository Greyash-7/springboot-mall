package com.recipe.mall.service;

import com.github.pagehelper.PageInfo;
import com.recipe.mall.MallApplicationTests;
import com.recipe.mall.enums.ResponseEnum;
import com.recipe.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IProductServiceTest extends MallApplicationTests {

    @Autowired
    private IProductService productService;

    @Test
    public void list(){
        ResponseVo<PageInfo> responseVo = productService.list(100028, 1, 4);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
