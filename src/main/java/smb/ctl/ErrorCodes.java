package smb.ctl;

/**
 */
public enum ErrorCodes {
    BUSINESS_ERROR("0005", "业务错误"),
    UNKWOWN_ERROR("9999", "未知错误"),
    SYSTEM_BUSY("9998", "系统繁忙"),
    SYSTEM_FORBID("9997","服务不支持"),
    USER_NOT_LOGIN("0001", "用户未登录"),
    AUTHCODE_NOMATCH("0010", "验证码验证错误"),
    SIGN_NO_PASS("0002","验签未通过"),
    SIGN_ADD_ERROR("0003", "加签失败"),
    DES_DECRYPT_ERROR("0004", "DES解密失败"),
    ;


    private String code;// 错误代码
    private String msg;// 错误信息

    ErrorCodes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

