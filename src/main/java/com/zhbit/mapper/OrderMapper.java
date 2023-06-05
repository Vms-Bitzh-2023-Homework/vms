package com.zhbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhbit.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

     List<Order> getOrder();

     List<Order> getOrderById(int id);

     List<Order> getOrderByName(String Name);

     boolean deleteOrder(int id);

     boolean addOrder(Order visitorInfo);

     boolean updateOrder(Order visitorInfo);

}
