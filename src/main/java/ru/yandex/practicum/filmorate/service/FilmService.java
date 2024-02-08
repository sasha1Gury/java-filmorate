package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;

    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film getFilmById(long id) {
        return filmStorage.getFilmById(id);
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public void deleteFilm(long id) {
        filmStorage.deleteFilm(id);
    }

     public void likeFilm(Long filmId, Long userId) {
         Film film = filmStorage.getFilmById(filmId);
         if (film != null && !film.getLikes().contains(userId)) {
             film.addLike(userId);
         }
     }

    public List<Film> getPopular(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted(Comparator.comparingInt(Film::getLikeCount).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    public void unlikeFilm(Long filmId, Long userId) {
        Film film = filmStorage.getFilmById(filmId);
        if (film == null) {
            throw new NotFoundException(String.format("Фильма с id " + filmId + " не существует"));
        }
        if (film.getLikes().contains(userId)) {
            film.deleteLike(userId);
        } else throw new NotFoundException(String.format("Пользователь с id " + userId + " не ставил лайк"));
    }
}
