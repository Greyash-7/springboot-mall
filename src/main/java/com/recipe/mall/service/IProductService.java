package com.recipe.mall.service;

import com.github.pagehelper.PageInfo;
import com.recipe.mall.vo.ProductDetailVo;
import com.recipe.mall.vo.ResponseVo;

public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);

    ResponseVo<Integer> num(Integer categoryId);

    ResponseVo<PageInfo> searchList(String name, Integer pageNum, Integer pageSize);

    ResponseVo<Integer> searchNum(String name);

    ResponseVo<PageInfo> favorList(Integer pageNum, Integer pageSize);

    ResponseVo<PageInfo> vegetableList(String name, Integer pageNum, Integer pageSize);
}
