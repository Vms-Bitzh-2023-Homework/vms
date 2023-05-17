package com.zhbit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;

    private String password;

    private String adPhone;

    private Integer perms;
}