package com.atguigu.lambda;

import java.util.concurrent.RecursiveTask;

/**
 * @author jdx
 * @date 2021-05-06 00:47
 * <p>
 * ForkJoin并发框架
 * 从JDK1.7开始，java提供ForkJoin框架用于并行执行任务，他的思想就是将一个大人物分割成若干个小任务，
 * 最终汇总每个小任务的结果得到这个大任务的结果
 * 两个特性：递归分治、工作密取
 * 举例：
 * 求1加到一亿的值。使用单线程的话就是1+2+3+4...+1亿
 * 使用ForkJoin的话就是1+2..+1000万（第一个线程）、1000万零一+1000万零2..2000万（第二个线程）。。。
 * 多个线程结果累加得出结论。
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 运算或者 任务拆分
     *
     * @return
     */
    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            //当前任务数据区间少于10000
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //当前任务数据区间大于10000 进行任务拆分
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
