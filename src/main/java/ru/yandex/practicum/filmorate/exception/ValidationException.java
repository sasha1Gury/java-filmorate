package ru.yandex.practicum.filmorate.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String s) {
        super(s);
    }

    public ValidationException() {
        super("Ошибка валидации");
    }
}