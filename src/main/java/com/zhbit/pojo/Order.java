package com.zhbit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private String phone;

    private Double money;

    private String carNumber;

    private String parkingTime;

    private String status;

}
