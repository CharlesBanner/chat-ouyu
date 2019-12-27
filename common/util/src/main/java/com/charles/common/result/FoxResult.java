package com.charles.common.result;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:29
 */
import com.alibaba.fastjson.annotation.JSONType;
import com.charles.common.enums.EnumInterface;
import com.charles.common.enums.ErrorEnum;

@JSONType(
        orders = {"success", "statusCode", "message", "data"}
)
public class FoxResult<T> {
    public static final FoxResult SUCCESS;
    public static final FoxResult FAIL;
    private boolean success;
    private int statusCode;
    private String message;
    private T data;

    public FoxResult() {
    }

    public FoxResult(T data) {
        this(Boolean.TRUE, ErrorEnum.SUCCESS, (T) data);
    }

    public FoxResult(boolean success, EnumInterface error) {
        this(success, error.getValue(), error.getDesc(), (T) null);
    }

    public FoxResult(boolean success, EnumInterface error, String message) {
        this(success, error.getValue(), message, (T) null);
    }

    public FoxResult(boolean success, EnumInterface error, T data) {
        this(success, error.getValue(), error.getDesc(), data);
    }

    public FoxResult(boolean success, int statusCode, String message, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }



    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    static {
        SUCCESS = new FoxResult(Boolean.TRUE, ErrorEnum.SUCCESS);
        FAIL = new FoxResult(Boolean.FALSE, ErrorEnum.UNKOWN_ERROR);
    }
}
