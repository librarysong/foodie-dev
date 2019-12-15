package com.eat.exception;

import com.eat.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author weifei.song
 * @date 2019/12/15 20:05
 */
@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public IMOOCJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException exception) {
        return IMOOCJSONResult.errorMsg("文件上传大小不能超过500k");
    }
}
