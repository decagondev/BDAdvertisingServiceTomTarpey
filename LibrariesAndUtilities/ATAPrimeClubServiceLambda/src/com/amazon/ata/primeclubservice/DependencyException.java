package com.amazon.ata.primeclubservice;

public class DependencyException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  public DependencyException() {
  }

  public DependencyException(Throwable cause) {
    initCause(cause);
  }

  public DependencyException(String message) {
    super(message);
  }

  public DependencyException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }

  @Override
  public String getMessage() { 
    return super.getMessage();
  }
}