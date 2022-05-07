package com.recipe.mall.service;

import com.recipe.mall.vo.CategoryVo;
import com.recipe.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);

    ResponseVo<Integer> add(String name);

    ResponseVo<Integer> delete(String name);
}
