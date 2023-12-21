package com.amazon.ata.advertising.service.exceptions;

public class AdvertisementServiceException extends RuntimeException {
    public AdvertisementServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
