package com.zhbit.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhbit.mapper.InOutRecordMapper;
import com.zhbit.mapper.OrderMapper;
import com.zhbit.pojo.InOutRecord;
import com.zhbit.pojo.Order;
import com.zhbit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

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
        return orderMapper.deleteOrder(id);
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
