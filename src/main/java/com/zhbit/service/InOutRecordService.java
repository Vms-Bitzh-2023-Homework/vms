package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.pojo.InOutRecord;

import java.util.List;

public interface InOutRecordService extends IService<InOutRecord> {

    List<InOutRecord> getRecord();

    List<InOutRecord> getRecordBycarNo(String carNo);

    boolean deleteRecord(int id);

    boolean addRecord(InOutRecord inOutRecord);

    boolean updateRecord(InOutRecord inOutRecord);


}
