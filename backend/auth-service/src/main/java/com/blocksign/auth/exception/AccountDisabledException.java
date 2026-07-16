package com.blocksign.auth.exception;

public class AccountDisabledException extends RuntimeException {
  public AccountDisabledException(String message) {
    super(message);
  }
}
