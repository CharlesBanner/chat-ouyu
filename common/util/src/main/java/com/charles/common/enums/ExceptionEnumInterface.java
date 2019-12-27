package com.charles.common.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:30
 */
public interface ExceptionEnumInterface extends EnumInterface {
    static ExceptionEnumInterface getErrorEnum(int val) {
        ErrorEnum errorEnum = ErrorEnum.getErrorEnum(val);
        return errorEnum != null ? errorEnum : ErrorEnum.UNKOWN_ERROR;
    }

    int getValue();
}
