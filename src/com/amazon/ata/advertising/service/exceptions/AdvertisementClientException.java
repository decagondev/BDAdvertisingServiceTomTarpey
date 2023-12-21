package com.amazon.ata.advertising.service.exceptions;

public class AdvertisementClientException extends RuntimeException {
    public AdvertisementClientException(String message) {
        super(message);
    }

    public AdvertisementClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdvertisementClientException(Throwable cause) {
        super(cause);
    }

    public AdvertisementClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
