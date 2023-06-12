package com.zhbit.service.Impl;

import com.zhbit.mapper.ParkcarInfoMapper;
import com.zhbit.pojo.ParkcarInfo;
import com.zhbit.service.ParkcarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(rollbackFor = Exception.class)
@Service
public class ParkcarInfoServiceImpl implements ParkcarInfoService {

    @Autowired
    ParkcarInfoMapper parkcarInfoMapper;

    @Override
    public List<ParkcarInfo> getParkcarInfo() {
        return parkcarInfoMapper.getParkcarInfo();
    }

    @Override
    public List<ParkcarInfo> getParkcarInfoByParkID(Integer parkID) {
        return parkcarInfoMapper.getParkcarInfoByParkID(parkID);
    }

    @Override
    public boolean deleteParkcarInfo(int parkID) {
        parkcarInfoMapper.deleteParkcarInfo(parkID);
        return true;
    }

    @Override
    public boolean addParkcarInfo(ParkcarInfo parkcarInfo) {
        return parkcarInfoMapper.addParkcarInfo(parkcarInfo);
    }

    @Override
    public boolean updateParkcarInfo(ParkcarInfo parkcarInfo) {
        return parkcarInfoMapper.updateParkcarInfo(parkcarInfo);
    }
}
