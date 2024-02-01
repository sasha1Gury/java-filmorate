package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NewFilmException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    Map<Integer, Film> films = new HashMap<>();
    private int idCounter = 1;

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public Film addFilm(@Valid Film film) throws ValidationException, NewFilmException {
        if (isBefore1895(film)) {
            log.warn("Неверная дата фильма " + film.getName());
            throw new ValidationException();
        }
        if (films.containsValue(film)) {
            log.warn("Фильм " + film.getName() + " уже существует");
            throw new NewFilmException(film.getName());
        }
        film.setId(idCounter++);
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
            throw new ValidationException();
        }
        log.info("Фильм " + films.get(film.getId()).getName() +  " перезаписан на " + film.getName());
        films.put(film.getId(), film);
        return film;
    }

    public void deleteFilm(int id) {
        if (films.containsKey(id)) {
            films.remove(id);
        } else log.warn("фильма с id = " + id + " не существует");
    }

    private boolean isBefore1895(Film film) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return !film.getReleaseDate().isAfter(date);
    }
}
