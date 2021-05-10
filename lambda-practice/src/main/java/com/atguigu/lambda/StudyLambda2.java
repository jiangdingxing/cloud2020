package com.atguigu.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author jdx
 * @date 2021-05-05 23:05
 * <p>
 * 四大内置核心函数式接口：
 * 1.消费型接口      一个参数，无返回值
 * Consumer<T>         void accept(T t);
 * 2.供给型接口 无参数，有返回值
 * Supplier<T>         T ger();
 * 3.函数型接口 一个参数，有返回值
 * Function<T,R>       R app1y(T t);
 * 4.断言型接口    有参数，返回值为boolean类型
 * Predicate<T>        boolean test(T t);
 */
public class StudyLambda2 {

    //消费型Consumer  （有来无回）
    //需求：传入一个参数做业务处理，不需要返回值
    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test1() {
        happy(1000, (m) -> System.out.println("无忌每次去洗脚，每次消费" + m + "元"));
    }

    //供给型接口 Supplier （无来有回）
    //需求：产生指定数量的整数，放到集合中，返回集合
    public List<Integer> getNumList(int num, Supplier<Integer> sp) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            result.add(sp.get());
        }
        return result;
    }

    @Test
    public void test2() {
        List<Integer> numList = getNumList(5, () -> (int) (Math.random() * 100));
        numList.forEach(System.out::println);
    }

    //函数型接口 Function （有来有回）
    //需求：传入一个字符串，返回一个字符串
    public String strHandle(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    @Test
    public void test3() {
        String result=strHandle("武林盟主：",(x)->x+"无忌老师");
        System.out.printf(result);
    }

    //断言型接口 Predicate    传入一个参数返回一个boolean值
    //需求：对比数字大小返回boolean值
    public boolean compare(Integer num, Predicate<Integer> pre) {
        return pre.test(num);
    }

    @Test
    public void test4() {
        boolean result=compare(5,(x)->x>10);
        System.out.print(result);
    }
}
