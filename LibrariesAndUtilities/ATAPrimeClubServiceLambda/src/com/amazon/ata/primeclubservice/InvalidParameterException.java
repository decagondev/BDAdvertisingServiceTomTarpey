package com.amazon.ata.primeclubservice;

public class InvalidParameterException extends ClientFaultException {

  private static final long serialVersionUID = -1L;

  public InvalidParameterException() {
  }

  public InvalidParameterException(Throwable cause) {
    initCause(cause);
  }

  public InvalidParameterException(String message) {
    super(message);
  }

  public InvalidParameterException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}