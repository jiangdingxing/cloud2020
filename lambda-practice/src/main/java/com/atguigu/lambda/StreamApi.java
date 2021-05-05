package com.atguigu.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author jdx
 * @date 2021-05-06 00:07
 * <p>
 * Stream是Java8中处理集合的关键抽象概念，它可以指定你希望对
 * 集合进行的操作，可以执行非常复杂的查找、过滤和映射数据登操作。
 * 使用Stream Api对集合数据进行操作，就类似于使用SQL执行的数据
 * 库查询。也可以使用Stream Api来并行执行操作
 * <p>
 * 简而言之，StreamApi提供了一种高效且易于使用的处理数据方式
 */
public class StreamApi {

    /**
     * 数据源(集合、数组)->创建流->中间操作（一系列的流水线的操作）->终止操作->产生一个新流
     * 对原来的源不会产生任何的影响
     * ①Stream自己不会存储元素
     * ②Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream。
     * ③Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
     */

    //需求：学员的年龄大于25岁的
    List<Person> studentList = Arrays.asList(
            new Person("无忌", 18, 188, 35000),
            new Person("周芷若", 23, 178, 8000),
            new Person("赵敏", 18, 176, 9000),
            new Person("小昭", 18, 190, 10000)
    );

    //创建流
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        Person[] students = new Person[]{};
        Stream<Person> stream1 = Arrays.stream(students);

        Stream<String> steam = Stream.of("a", "b", "c");

        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
    }

    //终止操作：一次性执行全部内容-->“惰性求值”
    @Test
    public void test2() {
        Stream<Person> studentStream = studentList.stream().filter((x) -> x.getSalary() > 9000);
        studentStream.forEach(System.out::println);
        System.out.println("-------");
        studentList.forEach(System.out::println);
    }

    //map 映射- 接收Lambda，将元素转换成其他形式或提取信息
    //接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    @Test
    public void test3() {
        Stream<String> stream=Stream.of("aaa","bbb","ccc");
        stream.map((x)->x.toUpperCase())
                .forEach(System.out::println);
        studentList.stream().map(Person::getName).forEach(System.out::println);
    }
}
