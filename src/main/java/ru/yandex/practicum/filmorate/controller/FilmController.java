package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NewFilmException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public void addFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (!isAfter1895(film)) {
            throw new ValidationException();
        }
        films.put(film.hashCode(), film);
    }

    @PutMapping
    public void updateFilm(@Valid @RequestBody Film film) throws NewFilmException{
        if(films.containsValue(film)){
            throw new NewFilmException(film.getName());
        }
        films.put(film.hashCode(), film);
    }

    private boolean isAfter1895(Film film) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return film.getReleaseDate().isAfter(date);
    }

}
