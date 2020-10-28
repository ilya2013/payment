package ru.ibesh.service;

public class InvalidPaymentPurpose extends RuntimeException {
    public InvalidPaymentPurpose() {
    }

    public InvalidPaymentPurpose(String message) {
        super(message);
    }

    public InvalidPaymentPurpose(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPaymentPurpose(Throwable cause) {
        super(cause);
    }

    public InvalidPaymentPurpose(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
