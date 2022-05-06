package com.recipe.mall.controller;

import com.github.pagehelper.PageInfo;
import com.recipe.mall.service.IProductService;
import com.recipe.mall.vo.ProductDetailVo;
import com.recipe.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/products/detail")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) String name,
                                     @RequestParam(required = false, defaultValue="1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue="10") Integer pageSize){
        return iProductService.searchList(name, pageNum, pageSize);
    }

    @GetMapping("/products/{productId}")
    public ResponseVo<ProductDetailVo> detail(@PathVariable Integer productId){
        return iProductService.detail(productId);
    }

    @GetMapping("/products/num")
    public ResponseVo<Integer> num(@RequestParam(required = true) Integer categoryId){
        return iProductService.num(categoryId);
    }

    @GetMapping("/products/detail/num")
    public ResponseVo<Integer> searchNum(@RequestParam(required = false) String name){
        return iProductService.searchNum(name);
    }

    @GetMapping("/products/favorlist")
    public ResponseVo<PageInfo> favorList(@RequestParam(required = false, defaultValue="1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue="5") Integer pageSize){
        return iProductService.favorList(pageNum, pageSize);
    }

    @GetMapping("/products/vegetablelist")
    public ResponseVo<PageInfo> vegetableList(@RequestParam(required = false, defaultValue="笋") String name,
                                              @RequestParam(required = false, defaultValue="1") Integer pageNum,
                                              @RequestParam(required = false, defaultValue="5") Integer pageSize){
        return iProductService.vegetableList(name, pageNum, pageSize);
    }
}
