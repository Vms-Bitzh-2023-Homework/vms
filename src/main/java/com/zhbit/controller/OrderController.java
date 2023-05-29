package com.zhbit.controller;

import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.Order;
import com.zhbit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public Result getOrder(){
        List<Order> visitorInfo = orderService.getOrder();
        Integer statusCode = visitorInfo != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = visitorInfo != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,visitorInfo);
    }

    @GetMapping("/{carNumber}")
    public Result getOrderByName(@PathVariable String carNumber) {
        List<Order> visitorInfoByName = orderService.getOrderByName(carNumber);
        Integer statusCode = visitorInfoByName != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = visitorInfoByName != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,visitorInfoByName);
    }

    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable int id){
        List<Order> order=orderService.getOrderById(id);
        if(order.get(0).getStatus().equals("已付款"))
        {
            String msg = "Delete failed, status:已付款";
            return new Result(StatusCode.DELETE_ERR,msg);
        }
        boolean flag = orderService.deleteOrder(id);
        List<Order> visitorInfoById = orderService.getOrderById(id);
        String msg = visitorInfoById == null ? "delete success" : "Delete failed, please try again";
        return new Result(flag ? StatusCode.DELETE_OK:StatusCode.DELETE_ERR,msg);
    }

    @PostMapping
    public Result addOrder(@RequestBody Order order){
        boolean flag = orderService.addOrder(order);
        String msg = "save success";
        return new Result(flag ? StatusCode.UPDATE_OK:StatusCode.UPDATE_ERR,msg);
    }

    @PutMapping
    public Result updateOrder(@RequestBody Order order) {
        boolean flag = orderService.updateOrder(order);
        String msg = "update success";
        return new Result(flag ? StatusCode.SAVE_OK:StatusCode.SAVE_ERR,msg);
    }

}
