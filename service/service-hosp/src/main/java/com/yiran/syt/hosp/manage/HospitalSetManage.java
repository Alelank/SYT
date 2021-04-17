package com.yiran.syt.hosp.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiran.syt.common.exception.SYTException;
import com.yiran.syt.common.response.ResponseData;
import com.yiran.syt.common.response.ResponseEnum;
import com.yiran.syt.common.utils.MD5Util;
import com.yiran.syt.common.utils.Utils;
import com.yiran.syt.hosp.service.HospitalSetService;
import com.yiran.syt.model.hosp.HospitalSet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Ale on 2021/4/16
 */
@Component
public class HospitalSetManage {

    @Resource
    private HospitalSetService hospitalSetService;


    /**
     * 获取所有数据(分页)
     *
     * @return
     */
    public ResponseData doGetAll(JSONObject param) throws Throwable {
        if (param == null) {
            throw new RuntimeException("参数错误");
        }

        // 分页数据检查
        Integer currentPage = param.getInteger("currentPage");
        Integer limit = param.getInteger("limit");
        if (limit < 0 || currentPage < 0) {
            throw new RuntimeException("参数错误");
        }

        // 条件信息检查
        Page<HospitalSet> page = new Page<>(currentPage, limit);
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hosName = param.getString("hosName");
        if (!StringUtils.isEmpty(hosName)) {
            queryWrapper.eq("hosname", hosName);
        }
        String hosCode = param.getString("hosCode");
        if (!StringUtils.isEmpty(hosCode)) {
            queryWrapper.eq("hoscode", hosCode);
        }
        return ResponseData.success(hospitalSetService.page(page, queryWrapper));
    }

    /**
     * 批量删除/单个删除信息
     *
     * @param param
     * @return
     * @throws Throwable
     */
    public ResponseData doDelete(JSONObject param) throws Throwable {
        if (param == null) {
            throw new RuntimeException("参数错误");
        }

        JSONArray ids = param.getJSONArray("ids");
        if (ids.size() < 1) {
            return ResponseData.success();
        }
        return hospitalSetService.removeByIds(ids.toJavaList(String.class)) ? ResponseData.success() : ResponseData.err();
    }

    /**
     * 添加/更新医院信息
     *
     * @param param
     * @return
     */
    public ResponseData doAdd(JSONObject param) throws Throwable {
        if (param == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误");
        }

        // 基本数据验证
        long id = param.getLong("id");
        String hosName = param.getString("hosName");
        int status = param.getInteger("status");
        String hosCode = param.getString("hosCode");
        String contactsName = param.getString("contactsName");
        String contactsPhone = param.getString("contactsPhone");
        String apiUrl = param.getString("apiUrl");
        if (id < 0L || StringUtils.isEmpty(hosName) || StringUtils.isEmpty(hosCode)
                || StringUtils.isEmpty(contactsName) || StringUtils.isEmpty(contactsPhone)
                || StringUtils.isEmpty(apiUrl) || status < 0) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误");
        }

        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hosname", hosName);
        HospitalSet one = hospitalSetService.getOne(queryWrapper);
        if (null != one) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "已存在 ->" + hosName);
        }

        // 参数填充
        HospitalSet hospitalSet = new HospitalSet();
        hospitalSet.setHosName(hosName);
        hospitalSet.setHosCode(hosCode);
        hospitalSet.setContactsName(contactsName);
        hospitalSet.setContactsPhone(contactsPhone);
        hospitalSet.setApiUrl(apiUrl);
        hospitalSet.setStatus(status);

        boolean flag;
        if (id > 1) {
            hospitalSet.setId(id);
            flag = hospitalSetService.updateById(hospitalSet);
        } else {
            hospitalSet.setSignKey(MD5Util.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(1000)));
            flag = hospitalSetService.save(hospitalSet);
        }
        return flag ? ResponseData.success() : ResponseData.err();
    }


    /**
     * 根据id获取医院信息
     * @param param
     * @return
     */
    public ResponseData doGetHosp(JSONObject param) throws Throwable {
        if (param == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误");
        }
        long id = param.getLong("id");
        if (id < 1L) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误:" + id);
        }
        return ResponseData.success(getHospById(id));
    }

    /**
     * 获取医院信息
     *
     * @param id
     * @return
     * @throws Throwable
     */
    private HospitalSet getHospById(long id) throws Throwable {
        return hospitalSetService.getById(id);
    }


    /**
     * 锁定/解锁
     *
     * @param param
     * @return
     */
    public ResponseData doHospLock(JSONObject param) throws Throwable {
        if (param == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误:");
        }
        long id = param.getLong("id");
        if (id < 1) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误:" + id);
        }
        int status = param.getInteger("status");
        if (!Utils.in(status, 0, 1)) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误:" + status);
        }

        HospitalSet hosp = getHospById(id);
        if (hosp == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "暂无记录 -> hosp:" + hosp);
        }

        if (hosp.getStatus() == status) {
            return ResponseData.success();
        }
        hosp.setStatus(status);
        return hospitalSetService.updateById(hosp) ? ResponseData.success() : ResponseData.err();
    }

    /**
     * 发送签名秘钥
     *
     * @param param
     * @return
     */
    public ResponseData doSendKey(JSONObject param) throws Throwable {
        if (param == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误");
        }
        long id = param.getLong("id");
        if (id < 1) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "参数错误:" + id);
        }
        HospitalSet hosp = getHospById(id);
        if (hosp == null) {
            throw new SYTException(ResponseEnum.DATA_ERROR, "", "暂无记录 -> hosp:" + hosp);
        }
        String signKey = hosp.getSignKey();
        String hosCode = hosp.getHosCode();
        // TODO 发送签名秘钥
        return ResponseData.success();
    }
}
