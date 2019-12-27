package com.charles.common.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:30
 */
public enum ErrorEnum implements ExceptionEnumInterface {
    SUCCESS(1000, "SUCCESS", "成功"),
    UNKOWN_ERROR(2000, "FAIL", "未知异常"),
    INVALID_PARAMS(3001, "INVALID_PARAMS", "参数错误"),
    SEND_SMS_ERROR(4001, "SEND_SMS_ERROR", "短信发送异常");

    private int val;
    private String code;
    private String desc;

    private ErrorEnum(Integer value, String code, String desc) {
        this.val = value;
        this.code = code;
        this.desc = desc;
    }

    public static ErrorEnum getErrorEnum(int val) {
        ErrorEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ErrorEnum error = var1[var3];
            if (error.getValue() == val) {
                return error;
            }
        }

        throw new IllegalArgumentException("Param val mismatch.");
    }

    public int getValue() {
        return this.val;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
