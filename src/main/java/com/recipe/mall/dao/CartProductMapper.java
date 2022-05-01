package com.recipe.mall.dao;

import com.recipe.mall.pojo.CartProduct;
import com.recipe.mall.vo.CartProductVo;

public interface CartProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CartProduct record);

    int insertSelective(CartProduct record);

    CartProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartProduct record);

    int updateByPrimaryKey(CartProduct record);

    int insertSelective(CartProductVo cartProductVo);

    Integer selectBysuggest();
}
