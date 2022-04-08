package com.recipe.mall.vo;

import lombok.Data;

@Data
public class ProductDetailVo {
    private Integer id;

    private String categoryType;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String process;

    private String mainImage;

    private String comment;

    private String detail;

    private String analyse;

    private String tips;

    private Integer favor;

    private Integer status;
}
