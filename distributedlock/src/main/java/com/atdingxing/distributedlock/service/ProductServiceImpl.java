package com.atdingxing.distributedlock.service;

import com.atdingxing.distributedlock.entity.Product;
import com.atdingxing.distributedlock.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jdx
 * @date 2020-09-11 17:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;


    @Override
    public Product selectById(Integer id) {
        return productMapper.selectById(1);
    }

    @Override
    public List<Product> selectList() {
        return productMapper.selectList(null);
    }

    @Override
    public List<Product> getProductList() {
        return productMapper.getProductList();
    }


}
