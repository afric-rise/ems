package com.savio.ems.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormat<T> {
    private Integer code;
    private Boolean error;
    private String message;
    private T data;
}
