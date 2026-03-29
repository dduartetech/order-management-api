package com.diegoduarte.order_management_api.infrastructure.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String msg) {
        super(msg);
    }

    public ConflictException(String msg, Throwable throwable) {
        super(msg);
    }
}
