package com.atdingxing.distributedlock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author jdx
 * @date 2020-09-11 11:01
 *
 * 商品表
 */
@Data
@AllArgsConstructor  //全参构造函数
@NoArgsConstructor   //无参构造函数
@Accessors(chain = true) //链式赋值
public class Product {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 乐观锁版本
     */
    private Integer version;

}
