package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.LikeDbStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;

@Service
public class FilmDbService {

    private final FilmStorage filmDbStorage;
    private final LikeDbStorage likeDbStorage;

    public FilmDbService(@Qualifier("FilmDbStorage") FilmStorage filmDbStorage, LikeDbStorage likeDbStorage) {
        this.filmDbStorage = filmDbStorage;
        this.likeDbStorage = likeDbStorage;
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

    public List<Film> getPopular(int count) {
        return filmDbStorage.getPopular(count);
    }

    public void likeFilm(Long filmId, Long userId) {
        likeDbStorage.likeFilm(filmId, userId);
    }

    public void unlikeFilm(Long filmId, Long userId) {
        likeDbStorage.unlikeFilm(filmId, userId);
    }
}
