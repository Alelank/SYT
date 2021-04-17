package com.yiran.syt.common.exception;

import com.yiran.syt.common.response.ResponseData;
import com.yiran.syt.common.response.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Ale on 2021/4/17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseData error(Exception e) {
        e.printStackTrace();
        return ResponseData.err(e.toString(), ResponseEnum.DATA_ERROR);
    }


    @ExceptionHandler(SYTException.class)
    public ResponseData error(SYTException e) {
        e.printStackTrace();
        return ResponseData.err(e.toString(), e.getAmityMessage(), e.getResponseEnum());
    }
}
