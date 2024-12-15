package com.pgeasy.www;

import lombok.Builder;

@Builder
public class CommonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
