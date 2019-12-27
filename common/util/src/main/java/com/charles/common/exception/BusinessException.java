package com.charles.common.exception;

import com.charles.common.enums.EnumInterface;
import com.charles.common.enums.ErrorEnum;
import com.charles.common.enums.ExceptionEnumInterface;
import com.charles.common.result.FoxResult;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:39
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 236483930171554424L;
    private ExceptionEnumInterface exception;

    public BusinessException(ExceptionEnumInterface exception) {
        super(exception.getDesc());
        this.exception = exception;
    }

    public BusinessException(ExceptionEnumInterface exception, String message) {
        super(message);
        this.exception = exception;
    }

    public BusinessException(String message) {
        super(message);
        this.exception = ErrorEnum.UNKOWN_ERROR;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.exception = ErrorEnum.UNKOWN_ERROR;
    }

    public BusinessException(FoxResult exception) {
        super(ErrorEnum.UNKOWN_ERROR.getValue() == exception.getStatusCode() ? ErrorEnum.UNKOWN_ERROR.getDesc() : exception.getMessage());
        this.exception = ExceptionEnumInterface.getErrorEnum(exception.getStatusCode());
    }

    public EnumInterface getErrorCode() {
        return this.exception;
    }
}
