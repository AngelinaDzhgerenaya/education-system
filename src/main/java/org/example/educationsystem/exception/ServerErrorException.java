package org.example.educationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends Exception {
    public ServerErrorException() {
    super("Ошибка сервера");
  }
    public ServerErrorException(String message) {
    super(message);
  }
}
