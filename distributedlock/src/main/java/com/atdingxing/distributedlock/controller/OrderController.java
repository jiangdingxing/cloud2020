package com.atdingxing.distributedlock.controller;

import com.atdingxing.distributedlock.entity.Order;
import com.atdingxing.distributedlock.entity.Product;
import com.atdingxing.distributedlock.service.OrderService;
import com.atdingxing.distributedlock.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author jdx
 * @date 2020-09-11 17:30
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /*@Autowired
    RedisTemplate redisTemplate;*/

    @Autowired
    RedissonClient redissonClient;

    /**
     * request对象
     */
    @Autowired
    HttpServletRequest request;

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.selectById(id);
    }

    @GetMapping("/selectProduct")
    public List<Product> getProductById() {
        return productService.selectList();
    }

    @GetMapping("/getProductList")
    public List<Product> getProductList() {
        return productService.getProductList();
    }


    @GetMapping("/selectOrderList")
    public List<Order> selectList() {
        return orderService.selectList();
    }

    @GetMapping("/getOrderList")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    /**
     * 普通减库存
     *
     * @param id
     * @return
     */
    @GetMapping("/reduceStock/{id}")
    public String reduceStock(@PathVariable("id") Integer id) {
        /**
         * 分布式锁
         *  1. 独占性 -> 互斥性
         *      setnx  所谓 SETNX，是「SET if Not eXists」的缩写
         *      expire px  过期时间
         *
         * setnx()方法作用就是SET IF NOT EXIST，expire()方法就是给锁加一个过期时间。乍一看好像和前面的set()方法结果一样，
         * 然而由于这是两条Redis命令，不具有原子性，如果程序在执行完setnx()之后突然崩溃，导致锁没有设置过期时间。
         * 那么将会发生死锁。网上之所以有人这样实现，是因为低版本的jedis并不支持多参数的set()方法。
         *  2. 不能出现死锁
         *  redlock 红锁算法
         */
     /*   String randomStr = UUID.randomUUID().toString();
        Boolean redisLock = redisTemplate.boundValueOps(id).setIfAbsent(randomStr, 30, TimeUnit.SECONDS);
        if (!redisLock) {
            return "error";
        }*/
   /*     // 3.  容错性
        orderService.reduceStock(id);
        if (randomStr.equals(redisTemplate.boundValueOps(id).get().toString())) {
            redisTemplate.delete(id);
        }*/

        RLock lock = redissonClient.getLock(id.toString());
        lock.lock(30, TimeUnit.SECONDS);//30秒
        try {
            orderService.reduceStock(id);
        } finally {
            lock.unlock();
        }
        return "OK";
    }

}
