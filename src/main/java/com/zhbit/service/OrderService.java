package com.zhbit.service;

import com.zhbit.pojo.Order;
import com.zhbit.pojo.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrder();


    List<Order> getOrderById(int id);

    List<Order> getOrderByName(String Name);

    boolean deleteOrder(int id);

    boolean addOrder(Order visitorInfo);

    boolean updateOrder(Order visitorInfo);
}
