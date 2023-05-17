package com.zhbit.controller;

import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.ParkcarInfo;
import com.zhbit.service.ParkcarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parkcarInfo")
public class ParkcarInfoController {

    @Autowired
    ParkcarInfoService parkcarInfoService;

    @GetMapping
    public Result getParkcarInfo(){
        List<ParkcarInfo> parkcarInfo = parkcarInfoService.getParkcarInfo();
        Integer statusCode = parkcarInfo != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = parkcarInfo != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,parkcarInfo);
    }

    @GetMapping("/allParkcarInfo")
    public List<ParkcarInfo> getParkInfo(){
        return parkcarInfoService.getParkcarInfo();
    }

    @GetMapping("/{parkID}")
    public Result getParkcarInfoByParkID(@PathVariable Integer parkID) {
        List<ParkcarInfo> parkcarInfoByParkID = parkcarInfoService.getParkcarInfoByParkID(parkID);
        Integer statusCode = parkcarInfoByParkID != null ? StatusCode.GET_OK : StatusCode.GET_ERR;
        String msg = parkcarInfoByParkID != null ? "数据查询成功" : "数据查询失败，请重试！";
        return new Result(statusCode,msg,parkcarInfoByParkID);
    }

    @PostMapping
    public Result addParkcarInfo(@RequestBody Map<String, String> map){
        ParkcarInfo parkcarInfo = new ParkcarInfo();
        parkcarInfo.setParkID(Integer.parseInt(map.get("parkID")));
        parkcarInfo.setAllspace(Integer.parseInt(map.get("allspace")));
        parkcarInfo.setOccupied(Integer.parseInt(map.get("occupied")));
        boolean flag = parkcarInfoService.addParkcarInfo(parkcarInfo);
        String msg = "save success";
        return new Result(flag ? StatusCode.SAVE_OK:StatusCode.SAVE_ERR,msg);
    }

    @PutMapping
    public Result updateParkcarInfo(@RequestBody Map<String, String> map) {
        ParkcarInfo parkcarInfo = new ParkcarInfo();
        parkcarInfo.setParkNo(Integer.parseInt(map.get("parkNo")));
        parkcarInfo.setParkID(Integer.parseInt(map.get("parkID")));
        parkcarInfo.setAllspace(Integer.parseInt(map.get("allspace")));
        parkcarInfo.setOccupied(Integer.parseInt(map.get("occupied")));
        boolean flag = parkcarInfoService.updateParkcarInfo(parkcarInfo);
        String msg = "update success";
        return new Result(flag ? StatusCode.SAVE_OK:StatusCode.SAVE_ERR,msg);
    }

    @DeleteMapping("/{parkID}")
    public Result deleteParkcarInfo(@PathVariable int parkID){
        boolean flag = parkcarInfoService.deleteParkcarInfo(parkID);
        List<ParkcarInfo> parkcarInfoByParkID = parkcarInfoService.getParkcarInfoByParkID(parkID);
        System.out.println(parkcarInfoService.getParkcarInfoByParkID(parkID));
        String msg = parkcarInfoByParkID == null ? "删除该行车库信息成功!" : "删除失败，请重试！";;
        return new Result(flag ? StatusCode.DELETE_OK:StatusCode.DELETE_ERR,msg);
    }
}
