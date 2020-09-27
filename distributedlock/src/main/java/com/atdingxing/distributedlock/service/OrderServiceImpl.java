package com.atdingxing.distributedlock.service;


import com.alibaba.fastjson.JSONObject;
import com.atdingxing.distributedlock.entity.Order;
import com.atdingxing.distributedlock.entity.Product;
import com.atdingxing.distributedlock.mapper.OrderMapper;
import com.atdingxing.distributedlock.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author jdx
 * @date 2020-09-11 16:54
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;



    @Override
    public List<Order> selectList() {
        System.out.println("----selectAll method test----");
        List<Order> orderList = orderMapper.selectList(null);
        return orderList;
    }

    @Override
    public List<Order> getOrderList() {
        return orderMapper.getOrderList();
    }

    @Override
    public Integer addOrder(Order order) {
        return addOrder(order);
    }

    /**
     * 商品秒杀活动
     * 减库存
     * 乐观锁控制
     *
     * @return
     */
    @Transactional
    public void reduceStock(Integer id) {
        System.out.println("----抢购开始----");
        // 1.获取库存
        Product product = productMapper.getProduct(id);
        if (product.getStock() <= 0) {
            throw new RuntimeException("---库存不足---");
        }
        // 2.减库存
        /**
         * 普通减库存  在高并发的情况下会出现超卖的情况
         */
        int result=productMapper.reduceStock(id);
        /**
         *  乐观锁
         * 使用乐观锁来实现秒杀 ，在高并发的情况，因为线程速度很快，
         * 会导致用户拿到version版本过低导致无法修改库存，这种情况下会出现少卖的情况，
         * 要想用乐观锁实现秒杀的话，需要在前端不断重试，会导致用户一直重试，效果不好
         *
         * 乐观锁（Optimistic Lock），顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，
         * 但是在提交更新的时候会判断一下在此期间别人有没有去更新这个数据。乐观锁适用于读多写少的应用场景，这样可以提高吞吐量。
         *
         */
        //int result = productMapper.reduceStockByVersion(product.getId(), product.getVersion());
        /**
         *悲观锁
         *总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，
         * 这样别人想拿这个数据就会阻塞直到它拿到锁（共享资源每次只给一个线程使用，其它线程阻塞，
         * 用完后再把资源转让给其它线程）。传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，
         * 表锁等，读锁，写锁等，都是在做操作之前先上锁。Java中synchronized和ReentrantLock等独占锁就是悲观锁思想的实现。
         */
        //int result = productMapper.reduceStockByPessimistic(product.getId());

        /**
         * 分布式锁
         *  1. 独占性 -> 互斥性
         *      setnx  所谓 SETNX，是「SET if Not eXists」的缩写
         *      expire px  过期时间
         *
         *  2. 不能出现死锁
         *  3。 容错性
         *
         */

        if (result == 1) {
            Order order = new Order();
            System.out.print(UUID.randomUUID().toString());
            order.setUserId(UUID.randomUUID().toString()).setPid(id);
            orderMapper.addOrder(order);
        }
    }
}
