package com.yiran.syt.common.exception;

import com.yiran.syt.common.response.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Ale on 2021/4/17
 */
@ApiModel(value = "自定义全局异常")
public class SYTException extends RuntimeException{

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    private ResponseEnum responseEnum;

    @ApiModelProperty(value = "系统错误信息")
    private String sysMessage;

    @ApiModelProperty(value = "友好错误信息")
    private String amityMessage;


    public SYTException(ResponseEnum responseEnum, String sysMessage, String amityMessage) {
        this.responseEnum = responseEnum;
        this.code = responseEnum.getCode();
        this.sysMessage = sysMessage;
        this.amityMessage = amityMessage;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public Integer getCode() {
        return code;
    }

    public String getSysMessage() {
        return sysMessage;
    }

    public String getAmityMessage() {
        return amityMessage;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", sysMessage='" + sysMessage + '\'' +
                ", amityMessage='" + amityMessage + '\'' +
                '}';
    }
}
