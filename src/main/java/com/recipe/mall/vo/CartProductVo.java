package com.recipe.mall.vo;

import lombok.Data;

@Data
public class CartProductVo {
    private Integer id;

    public CartProductVo(Integer id, Integer quantity, String categoryType, int categoryId, String name, String subtitle, String process, String mainImage, Integer favor, Integer status, Boolean productSelected) {
        this.id = id;
        this.quantity = quantity;
        this.categoryType = categoryType;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.process = process;
        this.mainImage = mainImage;
        this.favor = favor;
        this.status = status;
        this.productSelected = productSelected;
    }

    private Integer quantity;//数量

    private String categoryType;

    private int categoryId;

    private String name;

    private String subtitle;

    private String process;

    private String mainImage;

    private Integer favor;

    private Integer status;

    private Boolean productSelected;
}
