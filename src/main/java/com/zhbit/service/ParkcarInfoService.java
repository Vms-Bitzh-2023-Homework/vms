package com.zhbit.service;

import com.zhbit.pojo.ParkcarInfo;

import java.util.List;

public interface ParkcarInfoService {

    List<ParkcarInfo> getParkcarInfo();

    List<ParkcarInfo> getParkcarInfoByParkID(Integer parkID);


    boolean deleteParkcarInfo(int parkID);

    boolean addParkcarInfo(ParkcarInfo parkcarInfo);

    boolean updateParkcarInfo(ParkcarInfo parkcarInfo);
}
