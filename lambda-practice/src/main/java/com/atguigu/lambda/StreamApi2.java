package com.atguigu.lambda;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author jdx
 * @date 2021-05-06 00:55
 */
public class StreamApi2 {

    //需求：计算0到一亿的和
    //单线程跑
    @Test
    public void test1() {
        Instant start = Instant.now();
        Long sum = 0L;
        for (int i = 0; i <= 100000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.printf("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    //测试 ForkJoin框架  线程使用cpu，cpu占用率百分百
    @Test
    public void test2() {
        Instant start = Instant.now();
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000L);
        Long sum = fjp.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.printf("耗费时间为：" + Duration.between(start, end).toMillis());
    }
}
