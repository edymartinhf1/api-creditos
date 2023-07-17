package com.bootcamp.bank.creditos.exception;

public class BusinessException extends RuntimeException{
    private String errorCode;
    private String messageError;

    public BusinessException(String messageError) {
        super(messageError);
        this.messageError = messageError;
    }

    public BusinessException(String errorCode,String messageError) {
        super(messageError);
        this.errorCode = errorCode;
        this.messageError = messageError;
    }


}
