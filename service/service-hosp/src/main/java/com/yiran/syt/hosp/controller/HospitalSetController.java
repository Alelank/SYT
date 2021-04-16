package com.yiran.syt.hosp.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiran.syt.common.response.ResponseData;
import com.yiran.syt.hosp.manage.HospitalSetManage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by Ale on 2021/4/16
 */
@Api(tags = "医院设置接口")
@RestController
@RequestMapping("/admin/hosp/set")
public class HospitalSetController {

    @Resource
    private HospitalSetManage hospitalSetManage;


    @ApiOperation(value = "获取医院信息")
    @RequestMapping(value = "/hosp", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseData getHosp(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doGetHosp(param);
    }


    @ApiOperation(value = "获取所有医院信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseData list(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doGetAll(param);
    }


    @ApiOperation(value = "添加医院信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseData add(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doAdd(param);
    }


    @ApiOperation(value = "批量/删除医院信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseData delete(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doDelete(param);
    }

}
