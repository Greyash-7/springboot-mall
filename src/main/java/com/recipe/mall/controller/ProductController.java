package com.recipe.mall.controller;

import com.github.pagehelper.PageInfo;
import com.recipe.mall.service.IProductService;
import com.recipe.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue="1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue="10") Integer pageSize){
        return iProductService.list(categoryId, pageNum, pageSize);
    }
}
