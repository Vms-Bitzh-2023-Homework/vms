package com.zhbit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.VisitorInfo;
import com.zhbit.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visitorInfo")
public class VisitorInfoController {

    @Autowired
    VisitorInfoService visitorInfoService;

    @GetMapping
    public Result getVisitorInfo(){
        List<VisitorInfo> visitorInfo = visitorInfoService.list();
        Integer statusCode = visitorInfo != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = visitorInfo != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,visitorInfo);
    }

    @GetMapping("/{visName}")
    public Result getVisitorInfoByName(@PathVariable String visName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("visName",visName);
        List<VisitorInfo> visitorInfoByName = visitorInfoService.listMaps(wrapper);
        Integer statusCode = visitorInfoByName != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = visitorInfoByName != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,visitorInfoByName);
    }

    @DeleteMapping("/{id}")
    public Result deleteVisitorInfo(@PathVariable int id){
        boolean flag = visitorInfoService.removeById(id);
        String msg = flag == true ? "delete success" : "Delete failed, please try again";
        return new Result(flag ? StatusCode.DELETE_OK:StatusCode.DELETE_ERR,msg);
    }

    @PostMapping
    public Result addVisitorInfo(@RequestBody VisitorInfo visitorInfo){
        boolean flag = visitorInfoService.save(visitorInfo);
        String msg = "save success";
        return new Result(flag ? StatusCode.SAVE_OK:StatusCode.SAVE_ERR,msg);
    }

    @PutMapping
    public Result updateVisitorInfo(@RequestBody VisitorInfo visitorInfo) {
        boolean flag = visitorInfoService.updateById(visitorInfo);
        String msg = "update success";
        return new Result(flag ? StatusCode.UPDATE_OK:StatusCode.UPDATE_ERR,msg);
    }
}
