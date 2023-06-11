package com.zhbit.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InOutRecord {

    @TableId("id")
    private Integer id;

    @TableField("carNo")
    private String carNo;

    @TableField("in_time")
    private LocalDateTime In_time;

    @TableField("out_time")
    private LocalDateTime Out_time;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
