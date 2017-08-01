package com.gzw.controller;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.ResultInfo;
import com.gzw.enums.ResultCode;
import com.gzw.utils.RsaUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gujian on 2017/7/18.
 */
@RestController
public class RsaController {

    @PostMapping(value = "/privateKey")
    public String getPrivateKey(){
        ResultInfo<String> resultInfo;
        try {
            String key = RsaUtil.getPrivateKeyStrOrCreate();
            resultInfo = ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,key);
        } catch (Exception e) {
            resultInfo = ResultInfo.getErrorInfo(ResultCode.REQUEST_ERROR.getResultCode(),e.getMessage());
            return JSON.toJSONString(resultInfo);
        }

        return JSON.toJSONString(resultInfo);
    }

    @PostMapping(value = "/publicKey")
    public String getPublicKey(){
        ResultInfo<String> resultInfo;
        try {
            String key = RsaUtil.getPublicKeyStrOrCreate();
            resultInfo = ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,key);
        } catch (Exception e) {
            resultInfo = ResultInfo.getErrorInfo(ResultCode.REQUEST_ERROR.getResultCode(),e.getMessage());
            return JSON.toJSONString(resultInfo);
        }

        return JSON.toJSONString(resultInfo);
    }

    @PostMapping(value = "/jiemi")
    public String jiemi(@RequestParam String data){
        ResultInfo<String> resultInfo;
        try {
            String datas = RsaUtil.decryptByPrivateKey(data);
            String da = new String(datas);
            resultInfo = ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,da);
        } catch (Exception e) {
            resultInfo = ResultInfo.getErrorInfo(ResultCode.REQUEST_ERROR.getResultCode(),e.getMessage());
            return JSON.toJSONString(resultInfo);
        }
        return JSON.toJSONString(resultInfo);
    }
}
