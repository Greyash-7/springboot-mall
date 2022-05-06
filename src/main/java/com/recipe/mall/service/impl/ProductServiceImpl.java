package com.recipe.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.recipe.mall.dao.ProductMapper;
import com.recipe.mall.pojo.Product;
import com.recipe.mall.service.ICategoryService;
import com.recipe.mall.service.IProductService;
import com.recipe.mall.vo.ProductDetailVo;
import com.recipe.mall.vo.ProductVo;
import com.recipe.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if(categoryId != null){
            categoryService.findSubCategoryId(categoryId, categoryIdSet);
            categoryIdSet.add(categoryId);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);

        return ResponseVo.success(productDetailVo);
    }

    @Override
    public ResponseVo<Integer> num(Integer categoryId) {
        int productNum = productMapper.productNum(categoryId);
        return ResponseVo.success(productNum);
    }

    @Override
    public ResponseVo<PageInfo> searchList(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByNameSet(name);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<Integer> searchNum(String name) {
        int searchNum = productMapper.searchNum(name);
        return ResponseVo.success(searchNum);
    }

    @Override
    public ResponseVo<PageInfo> favorList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByFavor();
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> vegetableList(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
//        name = "笋";
        List<Product> productList = productMapper.selectByVegetable(name);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productList);
        return ResponseVo.success(pageInfo);
    }
}
