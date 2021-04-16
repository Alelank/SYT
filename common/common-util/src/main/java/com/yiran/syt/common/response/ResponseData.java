package com.yiran.syt.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * Created by Ale on 2021/4/16
 */
@ApiModel(value = "全局统一响应结果")
public class ResponseData<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "响应是否正常")
    private boolean success;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Integer getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData() {
    }

    public static <T> ResponseData<T> success() {
        return success(null);
    }

    public static <T> ResponseData<T> success(T data){
       return success(data,"");
    }

    public static <T> ResponseData<T> success(T data, String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ResponseEnum.SUCCESS.getCode());
        responseData.setSuccess(true);
        responseData.setMessage(message);
        if (data != null) {
            responseData.setData(data);
        }
        return responseData;
    }

    public static <T> ResponseData<T> err() {
        return err("", ResponseEnum.FAIL);
    }

    public static <T> ResponseData<T> err(String message, ResponseEnum responseEnum) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ResponseEnum.FAIL.getCode());
        responseData.setSuccess(false);
        if (!StringUtils.isEmpty(message) && null != responseEnum) {
            responseData.setMessage(responseEnum.getMessage());
        } else if (!StringUtils.isEmpty(message) && null == responseEnum) {
            responseData.setMessage(message);
        } else {
            responseData.setMessage(responseEnum.getMessage());
        }
        return responseData;
    }
}
