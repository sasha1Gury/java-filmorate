package ru.yandex.practicum.filmorate.exception;

public class NewFilmException extends RuntimeException {
    public NewFilmException(String name) {
        super("Фильм " + name + " уже существует");
    }
}
