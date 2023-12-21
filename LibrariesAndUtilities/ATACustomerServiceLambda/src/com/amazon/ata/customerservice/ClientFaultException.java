package com.amazon.ata.customerservice;

public abstract class ClientFaultException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  public ClientFaultException() {
  }

  public ClientFaultException(Throwable cause) {
    initCause(cause);
  }

  public ClientFaultException(String message) {
    super(message);
  }

  public ClientFaultException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }

  @Override
  public String getMessage() { 
    return super.getMessage();
  }
}