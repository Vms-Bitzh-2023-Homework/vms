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


            Duration duration = Duration.between(inTime, outTime);
            long seconds = duration.getSeconds(); // 计算时间差的秒数
            String diffTime = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60); // 构建时间差字符串
            double price = ((double) seconds / 60) * 0.0167; // 计算价格

            Order order = new Order();
            order.setCarNumber(inOutRecord.getCarNo());
            order.setStatus(Constant.status2);
            order.setParkingTime(diffTime);
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
