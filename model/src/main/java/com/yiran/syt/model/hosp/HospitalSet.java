package com.yiran.syt.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yiran.syt.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *     HospitalSet
 * </p>
 * Created by Ale on 2021/4/16
 */
@ApiModel(description = "医院设置")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {

    @ApiModelProperty(value = "医院名称")
    @TableField("hosname")
    private String hosName;

    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hosCode;

    @ApiModelProperty(value = "api基础路径")
    @TableField("api_url")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    @TableField("sign_key")
    private String signKey;

    @ApiModelProperty(value = "联系人姓名")
    @TableField("contacts_name")
    private String contactsName;

    @ApiModelProperty(value = "联系人手机")
    @TableField("contacts_phone")
    private String contactsPhone;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HospitalSet{" +
                "hosName='" + hosName + '\'' +
                ", hosCode='" + hosCode + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", signKey='" + signKey + '\'' +
                ", contactsName='" + contactsName + '\'' +
                ", contactsPhone='" + contactsPhone + '\'' +
                ", status=" + status +
                '}';
    }
}
