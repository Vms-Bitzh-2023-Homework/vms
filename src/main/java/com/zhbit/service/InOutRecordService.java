package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.pojo.InOutRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface InOutRecordService extends IService<InOutRecord> {

    List<InOutRecord> getRecord();

    List<InOutRecord> getRecordByCarNo(String carNo);

    boolean deleteRecord(int id);

    boolean addRecord(InOutRecord inOutRecord);

    boolean updateRecord(InOutRecord inOutRecord);

    List<InOutRecord> inOutList(Map<String,String> map);

}
