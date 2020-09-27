package com.atdingxing.distributedlock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jdx
 * @date 2020-09-11 10:55
 *
 *      乐观锁
 *      悲观锁
 *      分布式锁demo
 *
 */
@SpringBootApplication
@MapperScan("com.atdingxing.distributedlock.mapper")
public class DistributedLockMain {

    public static void main(String[] args){
        SpringApplication.run(DistributedLockMain.class,args);
    }
}
