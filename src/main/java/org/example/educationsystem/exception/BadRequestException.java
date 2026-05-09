package org.example.educationsystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException() {
        super("Неверный запрос"); }

    public BadRequestException(String message) {
        super(message);
    }
}
