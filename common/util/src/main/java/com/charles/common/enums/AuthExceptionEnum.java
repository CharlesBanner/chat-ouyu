package com.charles.common.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:44
 */
public enum AuthExceptionEnum implements ExceptionEnumInterface {
    /**
     * 验证结果枚举类
     */
    USERNAME_OR_PWD_ERROR(5200, "USERNAME_OR_PWD_ERROR", "用户名或密码错误"),
    NEED_LOGIN(Integer.valueOf(5201), "NEED_LOGIN", "请校验Token"),
    REDIRECT_TO_LOGIN_PAGE(Integer.valueOf(5202), "REDIRECT_TO_LOGIN_PAGE", "跳转到登录页面"),
    NOT_LOGIN(Integer.valueOf(5203), "NOT_LOGIN", "未登录"),
    LOGIN_ELSEWHERE(Integer.valueOf(5204), "LOGIN_ELSEWHERE", "已在其它地方登录"),
    NO_NEED_HANDLE(Integer.valueOf(5205), "NO_NEED_HANDLE", "已经返回5201，不需要处理"),
    TIME_OUT(Integer.valueOf(5206), "TIME_OUT", "登录超时"),
    INVALID_TICKET(Integer.valueOf(5607), "INVALID_TICKET", "无效的Ticket"),
    TICKET_IS_NULL(Integer.valueOf(5608), "TICKET_IS_NULL", "未获取到Ticket"),
    NETWORK_ERROR(Integer.valueOf(5609), "NETWORK_ERROR", "网络异常"),
    REMOVE_SESSION_FAILURE(Integer.valueOf(5610), "REMOVE_SESSION_FAILURE", "移除session失败");

    private int val;
    private String code;
    private String desc;

    private AuthExceptionEnum(int val, String code, String desc) {
        this.val = val;
        this.code = code;
        this.desc = desc;
    }

    public static AuthExceptionEnum getErrorEnum(int val) {
        AuthExceptionEnum[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            AuthExceptionEnum error = var1[var3];
            if (error.getValue() == val) {
                return error;
            }
        }

        return null;
    }

    @Override
    public int getValue() {
        return this.val;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

}
