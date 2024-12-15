package com.pgeasy.www;

import lombok.Builder;

@Builder
public record CommonResponse<T>(Integer code, String message, T data) {
    @Override
    public String toString() {
        return
               """
               CommonResponse{
               code=%s,
               message='%s',
               data=%s
               }
               """.formatted(code, message, data);
    }
}
