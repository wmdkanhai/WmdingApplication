package com.wmding.networklib.bean;

/**
 * 操作结果类
 */
public class Result {

    /**
     * 操作状态码
     */
    private String code;
    /**
     * 操作结果/错误信息
     */
    private String msg;

    public Result() {
    }

    /**
     * 构造函数
     *
     * @param code 操作状态码
     * @param msg  操作状态码/错误信息
     */
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
