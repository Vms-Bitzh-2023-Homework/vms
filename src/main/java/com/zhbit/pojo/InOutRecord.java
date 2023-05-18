package com.zhbit.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InOutRecord {

    @TableId("id")
    private int id;

    @TableField("carNo")
    private String carNo;

    @TableField("in_time")
    private LocalDateTime In_time;

    @TableField("out_time")
    private LocalDateTime Out_time;

}
