package me.fulln.infrastructure.constant;

public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(0, "success"),
    /**
     * 失败
     */
    FAIL(500, "fail");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
