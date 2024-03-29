package com.recipe.mall.dao;

import com.recipe.mall.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CategoryMapper {
    @Select("select * from mall_category where id = #{id}")
    Category findById(@Param("id") Integer id);

    Category queryById(Integer id);
}
