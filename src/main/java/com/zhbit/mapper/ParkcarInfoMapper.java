package com.zhbit.mapper;

import com.zhbit.pojo.ParkcarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkcarInfoMapper {

    List<ParkcarInfo> getParkcarInfo();

    ParkcarInfo getParkcarInfoByParkNo(int parkNo);

    List<ParkcarInfo> getParkcarInfoByParkID(int parkID);

    boolean deleteParkcarInfo(int parkID);

    boolean addParkcarInfo(ParkcarInfo parkcarInfo);

    boolean updateParkcarInfo(ParkcarInfo parkcarInfo);

}
