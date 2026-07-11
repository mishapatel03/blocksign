package com.blocksign.document.exception;

public class DocumentAlreadyExistsException extends RuntimeException {
  public DocumentAlreadyExistsException(String message) {
    super(message);
  }
}
