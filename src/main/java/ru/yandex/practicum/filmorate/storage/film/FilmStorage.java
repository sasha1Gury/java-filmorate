package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    public Film addFilm(Film film);

    public Film updateFilm(Film film);

    public void deleteFilm(long id);

    public List<Film> getAllFilms();

    public Film getFilmById(Long id);

    public List<Film> getPopular(int count);
}
