package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NewFilmException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();
    private int idCounter = 1;

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public Film addFilm(Film film) {
        if (isBefore1895(film)) {
            log.warn("Неверная дата фильма " + film.getName());
            throw new ValidationException(String.format(
                    "Неверная дата фильма  %s",
                    film.getName()));
        }

        if (films.containsValue(film)) {
            log.warn("Фильм " + film.getName() + " уже существует");
            throw new NewFilmException(String.format(
                    "Фильм %s уже существует",
                    film.getName()));
        }

        film.setId(idCounter++);
        film.setLikes(new HashSet<>());
        films.put(film.getId(), film);
        log.info("Создан фильм " + film.getName());

        return film;
    }

    public Film updateFilm(Film film) throws ValidationException, NewFilmException {
        if (films.containsValue(film)) {
            throw new NewFilmException(film.getName());
        }

        if (isBefore1895(film)) {
            log.warn("Неверная дата фильма " + film.getName());
            throw new ValidationException(String.format("Неверная дата фильма " + film.getName()));
        }

        if (films.containsKey(film.getId())) {
            film.setLikes(new HashSet<>());
            log.info("Фильм " + films.get(film.getId()).getName() +  " перезаписан на " + film.getName());
            films.put(film.getId(), film);
        } else throw new NotFoundException(String.format("Фильм с id " + film.getId() + " не найден "));

        return film;
    }

    public void deleteFilm(long id) {
        if (films.containsKey(id)) {
            films.remove(id);
        } else {
            log.warn("фильма с id = " + id + " не существует");
            throw new NotFoundException("фильма с id = " + id + " не существует");
        }
    }

    private boolean isBefore1895(Film film) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return !film.getReleaseDate().isAfter(date);
    }

    public Film getFilmById(Long id) {
        if (films.get(id) == null) {
            log.warn("фильма с id = " + id + " не существует");
            throw new NotFoundException(String.format("фильма с id = " + id + " не существует"));
        }

        return films.get(id);
    }
}
