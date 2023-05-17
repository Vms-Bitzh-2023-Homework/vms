package com.zhbit.service.Impl;

import com.zhbit.mapper.OrderMapper;
import com.zhbit.mapper.OrderMapper;
import com.zhbit.pojo.Order;
import com.zhbit.pojo.Order;
import com.zhbit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getOrder() {
        return orderMapper.getOrder();
    }

    @Override
    public List<Order> getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByName(String Name) {
        return orderMapper.getOrderByName(Name);
    }

    @Override
    public boolean deleteOrder(int id) {
        orderMapper.deleteOrder(id);
        return true;
    }

    @Override
    public boolean addOrder(Order visitorInfo) {
        return orderMapper.addOrder(visitorInfo);
    }

    @Override
    public boolean updateOrder(Order visitorInfo) {
        return orderMapper.updateOrder(visitorInfo);
    }
}
