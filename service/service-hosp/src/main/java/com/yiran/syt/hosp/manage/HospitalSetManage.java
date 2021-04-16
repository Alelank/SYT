package com.yiran.syt.hosp.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiran.syt.common.response.ResponseData;
import com.yiran.syt.common.utils.MD5Util;
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
     * 获取所有数据
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
     * 删除/批量
     * @param param
     * @return
     * @throws Throwable
     */
    public ResponseData doDelete(JSONObject param) throws Throwable {
        if (param == null) {
            throw new RuntimeException("参数错误");
        }

        JSONArray ids = param.getJSONArray("ids");
        if(ids.size() < 1){
            return ResponseData.success();
        }
        return hospitalSetService.removeByIds(ids.toJavaList(String.class)) ? ResponseData.success() : ResponseData.err();
    }

    /**
     * 添加数据
     * @param param
     * @return
     */
    public ResponseData doAdd(JSONObject param) throws Throwable{
        if (param == null) {
            throw new RuntimeException("参数错误");
        }

        // 基本数据验证
        String hosName = param.getString("hosName");
        String hosCode = param.getString("hosCode");
        String contactsName = param.getString("contactsName");
        String contactsPhone = param.getString("contactsPhone");
        String apiUrl = param.getString("apiUrl");
        if(StringUtils.isEmpty(hosName) || StringUtils.isEmpty(hosCode)
                || StringUtils.isEmpty(contactsName) || StringUtils.isEmpty(contactsPhone)
                || StringUtils.isEmpty(apiUrl)){
            throw new RuntimeException("参数错误");
        }

        // 参数填充
        HospitalSet hospitalSet = new HospitalSet();
        hospitalSet.setHosName(hosName);
        hospitalSet.setHosCode(hosCode);
        hospitalSet.setContactsName(contactsName);
        hospitalSet.setContactsPhone(contactsPhone);
        hospitalSet.setStatus(1);
        hospitalSet.setApiUrl(apiUrl);
        hospitalSet.setSignKey(MD5Util.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(1000)));
        return hospitalSetService.save(hospitalSet) ? ResponseData.success() : ResponseData.err();
    }

    /**
     * 根据id获取医院信息
     * @param param
     * @return
     */
    public ResponseData doGetHosp(JSONObject param) throws Throwable{
        if (param == null) {
            throw new RuntimeException("参数错误");
        }
        long id = param.getLong("id");
        if(id < 1L){
            throw new  RuntimeException("参数错误");
        }
        return ResponseData.success(hospitalSetService.getById(id));
    }
}
