package com.example.springquiz.exception;

import com.example.springquiz.enumeration.ErrorCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomizedRuntimeException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomizedRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
