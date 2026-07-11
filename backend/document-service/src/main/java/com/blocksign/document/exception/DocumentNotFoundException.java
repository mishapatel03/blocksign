package com.blocksign.document.exception;

public class DocumentNotFoundException extends RuntimeException {
  public DocumentNotFoundException(String message) {
    super(message);
  }
}
