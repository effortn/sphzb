package com.en.sphzb.VO;

import lombok.Data;

/**
 * 返回页面数据结构体
 * create by en
 * at 2019/2/21 14:39
 **/
@Data
public class ResultVO<T> {

    /**
     * 编码值
     **/
    private int code;


    /**
     * 信息
     **/
    private String msg;

    /**
     * 数据
     **/
    private T data;

    public ResultVO() {

    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
