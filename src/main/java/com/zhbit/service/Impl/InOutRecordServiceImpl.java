package com.zhbit.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhbit.common.Constant;
import com.zhbit.mapper.InOutRecordMapper;
import com.zhbit.mapper.OrderMapper;
import com.zhbit.pojo.InOutRecord;
import com.zhbit.pojo.Order;
import com.zhbit.service.InOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InOutRecordServiceImpl extends ServiceImpl<InOutRecordMapper,InOutRecord> implements InOutRecordService {

    @Autowired
    InOutRecordMapper inOutRecordMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<InOutRecord> getRecord() {
        return inOutRecordMapper.getRecord();
    }

    @Override
    public List<InOutRecord> getRecordByCarNo(String carNo) {
        return inOutRecordMapper.getRecordByCarNo(carNo);
    }

    @Override
    public boolean deleteRecord(int id) {
        return inOutRecordMapper.deleteRecord(id);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean addRecord(InOutRecord inOutRecord) {

        try{
            Order order = new Order();
            order.setCarNumber(inOutRecord.getCarNo());
            order.setStatus(Constant.status1);

            orderMapper.addOrder(order);
            inOutRecordMapper.addRecord(inOutRecord);

            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean updateRecord(InOutRecord inOutRecord) {
        try {
            LocalDateTime inTime = inOutRecord.getIn_time();
            LocalDateTime outTime = inOutRecord.getOut_time();

            long secondsDiff = ChronoUnit.SECONDS.between(inTime, outTime); // 计算时间差，单位为秒
            String timestampDiff = String.valueOf(secondsDiff * 1000); // 将时间差转换为时间戳，单位为毫秒
            System.out.println(timestampDiff);
            Duration duration = Duration.between(inTime, outTime);
            long seconds = duration.getSeconds(); // 计算时间差的秒数
            double price = ((double) seconds / 60) * 0.0167; // 计算价格

            Order order = new Order();
            order.setCarNumber(inOutRecord.getCarNo());
            order.setStatus(Constant.status2);
            order.setParkingTime(timestampDiff);
            order.setMoney(price);

            orderMapper.updateOrder(order);
            inOutRecordMapper.updateRecord(inOutRecord);

            return true; // 更新成功
        } catch (Exception e) {
            return false; // 更新失败
        }
    }

    @Override
    public List<InOutRecord> inOutList(InOutRecord inOutRecord) {
        return inOutRecordMapper.inOutList(inOutRecord);
    }

}
