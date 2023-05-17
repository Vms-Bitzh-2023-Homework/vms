package com.zhbit.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkcarInfo implements Serializable {


    private Integer parkNo;
    private Integer parkID;
    private Integer allspace;
    private Integer occupied;
}
