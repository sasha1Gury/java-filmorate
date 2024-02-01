package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

public interface FilmStorage {
    public Film addFilm(Film film);
    public Film updateFilm(Film film);
    public void deleteFilm(int id);
}
