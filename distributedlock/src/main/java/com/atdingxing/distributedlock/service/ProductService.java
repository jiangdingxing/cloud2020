package com.atdingxing.distributedlock.service;

import com.atdingxing.distributedlock.entity.Product;


import java.util.List;

public interface ProductService {

    Product selectById(Integer id);

    List<Product> selectList();

    List<Product> getProductList();

}
