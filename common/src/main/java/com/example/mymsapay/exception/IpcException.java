package com.example.mymsapay.exception;

public class IpcException extends CustomException {

    private final IpcExceptionType ipcExceptionType;

    public IpcException(IpcExceptionType ipcExceptionType) {
        this.ipcExceptionType = ipcExceptionType;
    }

    @Override
    public CustomExceptionType getCustomExceptionType() {
        return ipcExceptionType;
    }
}
