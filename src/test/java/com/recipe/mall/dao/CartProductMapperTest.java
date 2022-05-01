package com.recipe.mall.dao;

import com.recipe.mall.MallApplicationTests;
import com.recipe.mall.pojo.CartProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CartProductMapperTest extends MallApplicationTests {

    @Autowired
    private CartProductMapper cartProductMapper;

    @Test
    void selectBysuggest() {
        int res = cartProductMapper.selectBysuggest();
        System.out.println(res);
    }

    @Test
    void selectByPrimaryKey() {
        CartProduct cartProduct = new CartProduct();
        cartProduct = cartProductMapper.selectByPrimaryKey(63);
        System.out.println(cartProduct);
    }
}
