package me.fulln.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import me.fulln.infrastructure.constant.ResultEnum;

@Getter
@Setter
public class Result<T> {

    private boolean success;
    private String message;
    private T data;
    private int code;

    public Result(boolean success, String message, T data, int code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "success", data, 200);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null, 500);
    }

    public static <T> Result<T> fail(ResultEnum message) {
        return new Result<>(false, message.getMessage(), null, message.getCode());
    }




}
