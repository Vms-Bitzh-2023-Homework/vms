package com.zhbit.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhbit.mapper.InOutRecordMapper;
import com.zhbit.pojo.InOutRecord;
import com.zhbit.service.InOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InOutRecordServiceImpl extends ServiceImpl<InOutRecordMapper,InOutRecord> implements InOutRecordService {

    @Autowired
    InOutRecordMapper inOutRecordMapper;

    @Override
    public List<InOutRecord> getRecord() {
        return inOutRecordMapper.getRecord();
    }

    @Override
    public List<InOutRecord> getRecordBycarNo(String carNo) {
        return inOutRecordMapper.getRecordBycarNo(carNo);
    }

    @Override
    public boolean deleteRecord(int id) {
        inOutRecordMapper.deleteRecord(id);
        return true;
    }

    @Override
    public boolean addRecord(InOutRecord inOutRecord) {
        return inOutRecordMapper.addRecord(inOutRecord);
    }

    @Override
    public boolean updateRecord(InOutRecord inOutRecord) {
        return inOutRecordMapper.updateRecord(inOutRecord);
    }
}
