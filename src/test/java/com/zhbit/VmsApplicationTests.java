package com.zhbit;

import com.zhbit.mapper.OrderMapper;
import com.zhbit.service.OrderService;
import com.zhbit.service.ParkcarInfoService;
import com.zhbit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class VmsApplicationTests {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    ParkcarInfoService parkcarInfoService;

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

    }

}
