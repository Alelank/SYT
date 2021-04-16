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

    /**
     * @param param
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "获取所有数据")
    public ResponseData list(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doGetAll(param);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "添加医院信息")
    public ResponseData add(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doAdd(param);
    }



    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据id批量删除，逻辑删除")
    public ResponseData delete(@RequestBody JSONObject param) throws Throwable {
        return hospitalSetManage.doDelete(param);
    }

}
