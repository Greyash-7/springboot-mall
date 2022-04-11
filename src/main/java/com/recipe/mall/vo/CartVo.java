package com.recipe.mall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;

    private Boolean selectedAll;

    private Integer cartTotalQuantity;
}
