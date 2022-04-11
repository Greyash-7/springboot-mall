package com.recipe.mall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.recipe.mall.MallApplicationTests;
import com.recipe.mall.form.CartAddForm;
import com.recipe.mall.form.CartUpdateForm;
import com.recipe.mall.vo.CartVo;
import com.recipe.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class ICartServiceTest extends MallApplicationTests {
    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(24);
        cartAddForm.setSelected(true);

//        cartService.add(1, cartAddForm);
        ResponseVo<CartVo> add = cartService.add(1, cartAddForm);
        log.info("list={}", gson.toJson(add));
    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(1);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void update(){
        CartUpdateForm form = new CartUpdateForm();
        form.setSelected(false);

        ResponseVo<CartVo> list = cartService.update(1, 20, form);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void delete(){

        ResponseVo<CartVo> list = cartService.delete(1, 20);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void selectAll(){

        ResponseVo<CartVo> list = cartService.selectAll(1);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void unSelectAll(){

        ResponseVo<CartVo> list = cartService.unSelectAll(1);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void sum(){

        ResponseVo<Integer> list = cartService.sum(1);
        log.info("list={}", gson.toJson(list));
    }
}
