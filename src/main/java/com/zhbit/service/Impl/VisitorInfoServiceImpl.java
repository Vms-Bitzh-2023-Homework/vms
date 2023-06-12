package com.zhbit.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhbit.mapper.VisitorInfoMapper;
import com.zhbit.pojo.VisitorInfo;
import com.zhbit.service.VisitorInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class VisitorInfoServiceImpl extends ServiceImpl<VisitorInfoMapper,VisitorInfo> implements VisitorInfoService {

}
