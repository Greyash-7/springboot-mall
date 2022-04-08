package com.recipe.mall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
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

    private Date createTime;

    private Date updateTime;
}
