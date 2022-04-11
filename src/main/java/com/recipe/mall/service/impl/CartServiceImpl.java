package com.recipe.mall.service.impl;

import com.google.gson.Gson;
import com.recipe.mall.dao.ProductMapper;
import com.recipe.mall.enums.ResponseEnum;
import com.recipe.mall.form.CartAddForm;
import com.recipe.mall.form.CartUpdateForm;
import com.recipe.mall.pojo.Cart;
import com.recipe.mall.pojo.Product;
import com.recipe.mall.service.ICartService;
import com.recipe.mall.vo.CartProductVo;
import com.recipe.mall.vo.CartVo;
import com.recipe.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddForm form) {

        Integer quantity = 1;
        Product product = productMapper.selectByPrimaryKey(form.getProductId());
        //判断是否存在
        if(product == null){
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }

        //写入redis key: cart_1
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        Cart cart =  new Cart();
        String value = ops.get(redisKey, String.valueOf(product.getId()));
        if(StringUtils.isEmpty(value)){
            //没有商品
            cart = new Cart(product.getId(), quantity, form.getSelected());
        }else {
            cart = gson.fromJson(value, Cart.class);
        }

        ops.put(String.format(CART_REDIS_KEY_TEMPLATE, uid),
                String.valueOf(product.getId()),
                gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        boolean selectAll = true;
        Integer cartTotalQuantity = 0;

        Map<String, String> entries = ops.entries(redisKey);
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()){
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);

            Product product = productMapper.selectByPrimaryKey(productId);
            if(product != null){
                CartProductVo cartProductVo = new CartProductVo(productId,
                        cart.getQuantity(),
                        product.getCategoryType(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getProcess(),
                        product.getMainImage(),
                        product.getFavor(),
                        product.getStatus(),
                        cart.getProductSelected());
                cartProductVoList.add(cartProductVo);

                if(!cart.getProductSelected()){
                    selectAll = false;
                }
            }

            cartTotalQuantity += cart.getQuantity();
        }

        cartVo.setSelectedAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartProductVoList(cartProductVoList);

        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        Cart cart =  new Cart();
        String value = ops.get(redisKey, String.valueOf(productId));
        if(StringUtils.isEmpty(value)){
            //没有商品。报错
            return  ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        cart = gson.fromJson(value, Cart.class);
        if(form.getQuantity() != null && form.getQuantity() >= 0){
            cart.setQuantity(form.getQuantity());
        }
        if(form.getSelected() != null){
            cart.setProductSelected(form.getSelected());
        }
        ops.put(redisKey, String.valueOf(productId), gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = ops.get(redisKey, String.valueOf(productId));
        if(StringUtils.isEmpty(value)){
            //没有商品。报错
            return  ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }

        ops.delete(redisKey, String.valueOf(productId));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(true);
            ops.put(redisKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(false);
            ops.put(redisKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }

        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer sum = listForCart(uid).stream()
                .map(Cart::getQuantity)
                .reduce(0, Integer::sum);

        return ResponseVo.success(sum);
    }

    private List<Cart> listForCart(Integer uid){
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = ops.entries(redisKey);

        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            cartList.add(gson.fromJson(entry.getValue(), Cart.class));
        }

        return cartList;
    }
}
