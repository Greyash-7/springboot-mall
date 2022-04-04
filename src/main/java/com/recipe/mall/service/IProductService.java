package com.recipe.mall.service;

import com.github.pagehelper.PageInfo;
import com.recipe.mall.vo.ResponseVo;

public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);
}
