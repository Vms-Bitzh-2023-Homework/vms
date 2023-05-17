package com.zhbit.mapper;

import com.zhbit.pojo.Order;
import com.zhbit.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

     List<Order> getOrder();

     List<Order> getOrderById(int id);

     List<Order> getOrderByName(String Name);

     boolean deleteOrder(int id);

     boolean addOrder(Order visitorInfo);

     boolean updateOrder(Order visitorInfo);

}
