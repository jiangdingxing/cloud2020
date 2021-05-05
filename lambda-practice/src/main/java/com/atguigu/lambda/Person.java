package com.atguigu.lambda;

/**
 * @author jdx
 * @date 2021-05-05 23:52
 */
public class Person {
    private String name;//姓名
    private Integer age;//年龄
    private Integer size;//身高
    private Integer salary;//薪水

    public Person() {

    }
    public Person(Integer age) {
        this.age = age;
        System.out.println(age);
    }

    public Person(String name, Integer age, Integer size, Integer salary) {
        this.name = name;
        this.age = age;
        this.size = size;
        this.salary = salary;
    }

    //重写toString方法
    //toString()是一种自我描述方法 本身返回的是 getClass().getName() + "@" +Integer.toHexString(hashCode());
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", size=" + size +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
