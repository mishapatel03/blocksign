package com.blocksign.signing.exception;

public class ContractNotFoundException extends RuntimeException {
  public ContractNotFoundException(String message) {
    super(message);
  }
}
