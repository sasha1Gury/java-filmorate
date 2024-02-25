package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;

@Service
public class FilmDbService {

    private final FilmStorage filmDbStorage;

    public FilmDbService(@Qualifier("FilmDbStorage") FilmStorage filmDbStorage) {
        this.filmDbStorage = filmDbStorage;
    }

    public Film addFilm(Film film) {
        return filmDbStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmDbStorage.updateFilm(film);
    }

    public void deleteFilm(long id) {
        filmDbStorage.deleteFilm(id);
    }

    public Film getFilmById(long id) {
        return filmDbStorage.getFilmById(id);
    }

    public List<Film> getAllFilms() {
        return filmDbStorage.getAllFilms();
    }
}
