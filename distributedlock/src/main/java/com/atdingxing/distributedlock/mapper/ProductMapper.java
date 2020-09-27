package com.atdingxing.distributedlock.mapper;

import com.atdingxing.distributedlock.entity.Product;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.List;

/**
 * @author jdx
 * @date 2020-09-11 16:33
 */
public interface ProductMapper extends BaseMapper<Product> {

    @Override
    Product selectById(Serializable id);

    @Override
    List<Product> selectList(Wrapper<Product> queryWrapper);

    List<Product> getProductList();

    @Select("select * from product where id=#{id} ")
    Product getProduct(@Param("id") Integer id);

    /**
     * 普通减库存
     *
     * @param id
     * @return 是否成功
     */
    @Update("update Product set stock=stock-1 where id=#{id}")
    Integer reduceStock(@Param("id") Integer id);

    /**
     * 乐观锁减库存
     *
     * @param id
     * @param version
     * @return 是否成功
     */
    @Update("update Product set stock=stock-1,version=version+1 where id=#{id} and version=#{version} ")
    Integer reduceStockByVersion(@Param("id") Integer id, @Param("version") Integer version);

    /**
     * 悲观锁减库存  select * from product where id=#{id} for update
     *
     * @param id
     * @return 是否成功
     */
    @Update("update Product set stock=stock-1 where id=#{id}  ")
    Integer reduceStockByPessimistic(@Param("id") Integer id);
}
