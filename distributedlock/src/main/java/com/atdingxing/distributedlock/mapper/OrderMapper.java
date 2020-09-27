package com.atdingxing.distributedlock.mapper;

import com.atdingxing.distributedlock.entity.Order;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author jdx
 * @date 2020-09-11 16:33
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Override
    List<Order> selectList(Wrapper<Order> queryWrapper);

    List<Order> getOrderList();

    Integer addOrder(Order order);
}
