package ru.yandex.practicum.filmorate.exception;

public class NewUserException extends RuntimeException {
    public NewUserException(String login) {
        super("Пользователь " + login + " уже существует");
    }
}