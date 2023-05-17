package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.pojo.VisitorInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VisitorInfoService extends IService<VisitorInfo> {

}
