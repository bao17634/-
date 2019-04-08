package com.qm.volkswagenChina.common.exception;

/**
 * @author licy on 2017/10/16.
 */
public class QmGlobalExceptException extends RuntimeException {

    public QmGlobalExceptException() {
        super();
    }

    public QmGlobalExceptException(String message) {
        super(message);
    }

    public QmGlobalExceptException(String message, Throwable cause) {
        super(message, cause);
    }

    public QmGlobalExceptException(Throwable cause) {
        super(cause);
    }

    protected QmGlobalExceptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
