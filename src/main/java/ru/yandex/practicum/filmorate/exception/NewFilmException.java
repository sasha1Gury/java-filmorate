package ru.yandex.practicum.filmorate.exception;

public class NewFilmException extends Exception {
    public NewFilmException(String name) {
        super("Такой фильм " + name + " уже существует");
    }
}
