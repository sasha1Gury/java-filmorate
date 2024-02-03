package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NewFilmException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    Map<Long, Film> films = new HashMap<>();
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    private int idCounter = 1;

    @GetMapping
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException, NewFilmException {
        if (!isAfter1895(film)) {
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

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException, NewFilmException {
        if (films.containsValue(film)) {
            throw new NewFilmException(film.getName());
        }
        if (!isAfter1895(film)) {
            log.warn("Неверная дата фильма " + film.getName());
            throw new ValidationException();
        }
        log.info("Фильм " + films.get(film.getId()).getName() +  " перезаписан на " + film.getName());
        films.put(film.getId(), film);
        return film;
    }

    private boolean isAfter1895(Film film) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return film.getReleaseDate().isAfter(date);
    }
}
