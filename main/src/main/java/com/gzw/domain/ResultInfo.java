package com.gzw.domain;

import com.gzw.enums.ResultCode;

import java.io.Serializable;

/**
 * Created by gujian on 2017/6/23.
 */
public class ResultInfo<T> implements Serializable{

    private int resultCode;

    private String message;

    private T data;

    public static ResultInfo getErrorInfo(ResultCode resultCode){
        return new ResultInfo(resultCode.getResultCode(),resultCode.getMessage());
    }

    public static ResultInfo getSuccessInfo(ResultCode resultCode){
        return new ResultInfo(resultCode.getResultCode(),resultCode.getMessage());
    }

    public static ResultInfo getSuccessInfo(String message){
        return new ResultInfo(100,message);
    }

    public static ResultInfo getErrorInfo(int code, String message){
        return new ResultInfo(code,message);
    }

    public static<T> ResultInfo getSuccessWithInfo(ResultCode resultCode, T data){
        return new ResultInfo(resultCode.getResultCode(),resultCode.getMessage(),data);
    }

    public ResultInfo(T data) {
        this.data = data;
    }

    public ResultInfo(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultInfo(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
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
}
