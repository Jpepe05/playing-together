package com.jpepe.playingtogether.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {
  public EntityNotFoundException(String reason) {
    super(HttpStatus.NOT_FOUND, reason);
  }

  public EntityNotFoundException() {
    super(HttpStatus.NOT_FOUND);
  }
}
