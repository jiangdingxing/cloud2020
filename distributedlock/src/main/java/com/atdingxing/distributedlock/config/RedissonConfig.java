package com.atdingxing.distributedlock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author jdx
 * @date 2020-09-24 20:48
 * <p>
 * redis 分布式锁配置
 */
@Configuration
public class RedissonConfig {

    @Bean
    RedissonClient redissonClient() {
        Config config = new Config();
        //单机模式  redis是协议地址必须加在主机前面
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
       /*
       集群方式
       config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:7181");*/
       RedissonClient redissonClient= Redisson.create(config);
        return redissonClient;
    }
}
