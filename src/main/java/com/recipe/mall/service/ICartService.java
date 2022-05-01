package com.recipe.mall.service;

import com.recipe.mall.form.CartAddForm;
import com.recipe.mall.form.CartUpdateForm;
import com.recipe.mall.vo.CartVo;
import com.recipe.mall.vo.ResponseVo;

public interface ICartService {

    ResponseVo<CartVo> add (Integer uid, CartAddForm form);

    ResponseVo<CartVo> list (Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);

    ResponseVo<Integer> suggest(Integer uid);
}
