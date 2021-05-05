package com.atguigu.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author jdx
 * @date 2021-05-05 18:43
 * <p>
 * Java8引入了一个新的操作符“->” 该操作符称为箭头操作符或Lambda操作符
 * 箭头操作符将Lambda表达式分成两部分：
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能。既Lambda体
 * 依赖于函数式接口，Lambda表达式既对接口的实现
 */
public class StudyLambda1 {

    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.printf("123");
            }
        };
    }

    /**
     * 语法1： 无参。无返回值
     */
    @Test
    public void testRunnable() {
        int i = 0;
        Runnable runnable = () -> {
            System.out.printf("123");
        };
    }

    /**
     * 语法2：有一个参数，无返回值
     */
    @Test
    public void test2() {
        Consumer com = (x) -> {
            System.out.println(x);
        };
        com.accept("一二三45");
    }

    /**
     * 语法三：只有一个参数，省略参数括号
     */
    @Test
    public void test3() {
        Consumer com = x -> {
            System.out.println(x);
        };
        com.accept("一二三45");
    }

    /**
     * 语法4：有两个以上的参数，有返回值，并且Lambda体中有多条语句
     */
    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("比较");
            return Integer.compare(x, y);
        };
        //调用上方重写后的方法进行输出
        System.out.print(com.compare(3, 1));
        //Lambda简化的就是这一块匿名内部类
        Comparator com2 = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    /**
     * 语法5：Lambda体中只有一条语句：return 和大括号都可以省略不写
     * Lambda表达式的参数列表数据类型可以省略不写，JVM编译器就通过上下文推断数据类型
     */
    @Test
    public void test5() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        //调用上方重写后的方法进行输出
        System.out.print(com.compare(3, 1));

        //jvm的上下文推断
        String[] str={"2","3"};//可以编译成功
        String[] str2;
        /*str={"2","3"};//编译失败*/
        //Lambda简化的就是这一块匿名内部类
        Comparator com2 = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    /**
     * 语法6：参数指定类型
     */
    public void test6() {
        Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
    }
    //上联：左右遇一括号省
    //下联：右侧推断类型省
    //横批：能省则省

}

