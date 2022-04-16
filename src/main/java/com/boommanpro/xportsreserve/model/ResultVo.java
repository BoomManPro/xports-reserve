package com.boommanpro.xportsreserve.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultVo<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(200, "", data);
    }

    public static ResultVo<Object> success() {
        return new ResultVo<>(200, "", null);
    }
}
