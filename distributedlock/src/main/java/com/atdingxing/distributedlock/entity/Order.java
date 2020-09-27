package com.atdingxing.distributedlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author jdx
 * @date 2020-09-11 10:49
 * <p>
 * 订单表
 */
@Data
@AllArgsConstructor  //全参构造函数
@NoArgsConstructor   //无参构造函数
@Accessors(chain = true) //链式赋值
@TableName("`order`") //解决mysql表名为mysql关键字的问题
public class Order {

    /**
     * 主键id
     * 解决:java.lang.IllegalArgumentException: argument type mismatch
     * 使用mybatis-plus碰到的问题，函数传参没问题，实体类没声明使用自增长的ID
     * 解决: 声明自增长id@TableId(type = IdType.AUTO)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     * <p>
     * 数据库字段为user_id mybatisplus会把字段user_id转换为驼峰命名法为userId，
     * 导致java字段user_id获取的解析出来后无法获取到数据，
     * 需要将数据库字段user_id改成userId才能访问到数据
     * 还可以使用配置关闭驼峰命名转换
     * mybatis-plus：
     * configuration:
     * map-underscore-to-camel-case: false
     */
    private String userId;

    /**
     * 商品id
     */
    private Integer pid;

}
