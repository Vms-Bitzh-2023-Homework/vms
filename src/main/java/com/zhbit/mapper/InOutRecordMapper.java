package com.zhbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhbit.pojo.InOutRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InOutRecordMapper extends BaseMapper<InOutRecord> {

    List<InOutRecord> getRecord();

    List<InOutRecord> getRecordByCarNo(String carNo);

    boolean deleteRecord(int id);

    boolean addRecord(InOutRecord inOutRecord);

    boolean updateRecord(InOutRecord inOutRecord);

    List<InOutRecord> inOutList(InOutRecord inOutRecord);

}
