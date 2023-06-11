package com.zhbit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.InOutRecord;
import com.zhbit.service.InOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inOutRecord")
public class InOutRecordController {

    @Autowired
    InOutRecordService inOutRecordService;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @GetMapping
    public Result getRecord(){
        List<InOutRecord> record = inOutRecordService.list();
        Integer statusCode = record != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = record != null ? "query success" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,record);
    }

    /**
     * 出入时间列表
     */
    @PostMapping("/inOutList")
    public Result inOutList(@RequestBody Map<String,String> map){
        List<InOutRecord> record = inOutRecordService.inOutList(map);
        Integer statusCode = record != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = record != null ? "query success" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,record);
    }

    @GetMapping("/{carNo}")
    public Result getRecordByCarNo(@PathVariable String carNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("carNo",carNo);
        List<InOutRecord> recordByCarNo = inOutRecordService.listMaps(wrapper);
        Integer statusCode = recordByCarNo != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = recordByCarNo != null ? "query success" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,recordByCarNo);
    }

    @DeleteMapping("/{id}")
    public Result deleteRecord(@PathVariable int id) {
        boolean flag = inOutRecordService.deleteRecord(id);
        String msg = "delete success";
        return new Result(flag ? StatusCode.DELETE_OK:StatusCode.DELETE_ERR,msg);
    }

    @PostMapping
    public Result addRecord(@RequestBody Map<String,String> map) {
        InOutRecord inOutRecord = new InOutRecord();
        inOutRecord.setCarNo(map.get("carNo"));
        inOutRecord.setIn_time(LocalDateTime.parse(map.get("in_time"),dateFormat));
        inOutRecord.setOut_time(map.get("out_time") == null ? null : LocalDateTime.parse(map.get("out_time"),dateFormat));
        boolean flag = inOutRecordService.addRecord(inOutRecord);
        String msg = "save success";
        return new Result(flag ? StatusCode.SAVE_OK:StatusCode.SAVE_ERR,msg);
    }

    @PutMapping
    public Result updateVisitorInfo(@RequestBody Map<String,String> map) {
        InOutRecord inOutRecord = new InOutRecord();
        inOutRecord.setId(Integer.parseInt(map.get("id")));
        inOutRecord.setCarNo(map.get("carNo"));
        inOutRecord.setIn_time(LocalDateTime.parse(map.get("in_time"),dateFormat));
        inOutRecord.setOut_time(map.get("out_time") == null ? null : LocalDateTime.parse(map.get("out_time"),dateFormat));
        boolean flag = inOutRecordService.updateRecord(inOutRecord);
        String msg = "update success";
        return new Result(flag ? StatusCode.UPDATE_OK:StatusCode.UPDATE_ERR,msg);
    }
}
