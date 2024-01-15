package ru.yandex.practicum.filmorate.exception;

public class NewFilmException extends Exception {
    public NewFilmException(String name) {
        super("Фильм " + name + " уже существует");
    }
}
