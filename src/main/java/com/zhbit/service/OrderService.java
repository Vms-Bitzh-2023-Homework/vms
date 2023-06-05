package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.pojo.Order;
import com.zhbit.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    List<Order> getOrder();


    List<Order> getOrderById(int id);

    List<Order> getOrderByName(String Name);

    boolean deleteOrder(int id);

    boolean addOrder(Order visitorInfo);

    boolean updateOrder(Order visitorInfo);
}
