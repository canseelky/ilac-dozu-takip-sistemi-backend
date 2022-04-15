package com.msku.drugdosemonitoringsystem.controllers.response;

import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse<T>{
    private String message;
    private ResponseTypes status;
    private T data;

    public DefaultResponse(ResponseTypes status, String message, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
