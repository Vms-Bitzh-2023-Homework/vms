package com.zhbit.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorInfo {

    @TableId("id")
    private Integer id;

    @TableField("visPhone")
    private String visPhone;

    @TableField("carNo")
    private String carNo;

    @TableField("visName")
    private String visName;

    @TableLogic
    @TableField("deleted")
    private int deleted;

}
