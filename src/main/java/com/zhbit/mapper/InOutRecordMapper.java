package com.zhbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhbit.pojo.InOutRecord;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface InOutRecordMapper extends BaseMapper<InOutRecord> {

    List<InOutRecord> getRecord();

    List<InOutRecord> getRecordByCarNo(String carNo);

    boolean deleteRecord(int id);

    boolean addRecord(InOutRecord inOutRecord);

    boolean updateRecord(InOutRecord inOutRecord);

    List<InOutRecord> inOutList(Map<String,String> map);

}
