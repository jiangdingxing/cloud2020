package com.atdingxing.distributedlock.service;

import com.atdingxing.distributedlock.entity.Order;


import java.util.List;

/**
 * @author jdx
 * @date 2020-09-11 16:44
 */
public interface OrderService {

     List<Order> selectList();

     List<Order> getOrderList();

     Integer addOrder(Order order);

     public void reduceStock(Integer id);
}
