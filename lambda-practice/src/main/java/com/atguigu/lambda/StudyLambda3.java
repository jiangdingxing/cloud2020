package com.atguigu.lambda;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jdx
 * @date 2021-05-05 23:34
 * <p>
 * 方法引用和对象引用 ::
 * 若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * 可以理解为方法引用是lambda表达式的另外一种表现形式
 */
public class StudyLambda3 {

    //对象::实例方法名
    @Test
    public void test1() {
        //Consumer<String> con=(x)-> System.out.println(x);
        PrintStream printStream = System.out;
        //要注意的是接口的抽象方法形参表、返回类型需要和调用类方法形参表 返回类型保持一致
        Consumer<String> con = System.out::println;
        con.accept("123456");
    }

    //类::静态方法名
    @Test
    public void test2() {
        //要注意的是接口的抽象方法的形参表、返回类型需要和调用的类方法形参表 返回类型保持一致
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com2 = Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test3() {
        //需求：比较两个字符字符串是否相等
        //要求：第一个参数是实例方法的调用者，第二个参数是发放的传入参数时
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
    }

    //构造器引用
    @Test
    public void test5() {
        //构造器引用是根据传入的参数来选择构造参数的。比如student是选的无参构造函数，
        //而student1选择的是age的有参构造函数
        //如果没有对象没有相应的构造方法会报错
        Supplier<Person> student = Person::new;
        Person person = student.get();
        Function<Integer, Person> student1 = Person::new;
        student1.apply(25);
    }

}
