package com.recipe.mall.controller;

import com.recipe.mall.service.ICategoryService;
import com.recipe.mall.vo.CategoryVo;
import com.recipe.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll() {
        return categoryService.selectAll();
    }

    @PostMapping("/categories/add")
    public ResponseVo<Integer> addCategory(@RequestParam(required = false) String name) {
        return categoryService.add(name);
    }

    @PostMapping("/categories/del")
    public ResponseVo<Integer> delCategory(@RequestParam(required = false) String name) {
        return categoryService.delete(name);
    }
}
