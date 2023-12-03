package com.demo.su.core.config.handler;

import lombok.Getter;

@Getter
public enum BizErrorCode implements ErrorCode {

    //  4xx 错误请求
    NO_TOKEN                    ("4010101", "token为空"),
    TOKEN_ILLEGAL               ("4010102", "token非法无效"),
    TOKEN_INVALID               ("4010103", "Token已失效"),
    USER_NOT_FOUND              ("4010204", "账号不存在"),
    USER_BLOCKED                ("4010205", "账号已被锁定,请联系管理员"),



    //  5xx 服务器处理异常
    SERVER_UNKNOWN_ERROR        ("5000001", "服务器未知异常"),
    SERVER_OPERATION_FAILED     ("5000002", "服务器操作失败"),
    PAGE_DATA_LOST              ("5000003", "当前页面数据丢失，请刷新重试"),
    REQUEST_VALID_ERROR         ("5000004", "请求验证异常"),
    DATA_EXIST                  ("5000005", "服务器中对应数据不存在"),
    DATA_REPEAT                 ("5000006", "数据重复"),
    PARAM_ERROR                 ("5000007", "参数错误"),
    DATA_PAD_FAILED             ("5000008", "数据填充异常"),
    TRANSCODE_FAILED            ("5000009", "转换编码失败"),
    RESOURCE_NOT_FOUND          ("5000010", "找不到资源文件"),

    NO_FILE                     ("5000101", "请选择文件"),
    FILE_INCORRECT              ("5000102", "请选择正确的文件与文件类型"),
    FILE_READ_FAILED            ("5000103", "文件读取失败"),
    FILE_TRANSFER_FAILED        ("5000104", "文件传输失败"),

    //  5001xxx 工程异常
    UNDEFINED_ERROR             ("5001000", ""),
    DATA_OUT_BOUND              ("5001001", "数据超出判断范围");

    private final String code;
    private final String message;

    BizErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

}