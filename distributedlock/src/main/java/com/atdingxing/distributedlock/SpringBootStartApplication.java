package com.atdingxing.distributedlock;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author jdx
 * @date 2020-09-13 22:43
 *
 * 将springCloud项目打包成war必备
 *
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        //DistributedLockMain
        return builder.sources(DistributedLockMain.class);
    }
}
