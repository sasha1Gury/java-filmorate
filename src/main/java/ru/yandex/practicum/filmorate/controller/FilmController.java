package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NewFilmException;
import ru.yandex.practicum.filmorate.model.Film;

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
    public void addFilm(@RequestBody Film film) {

        films.put(film.hashCode(), film);
    }

    @PutMapping
    public void updateFilm(@RequestBody Film film) throws NewFilmException{
        if(films.containsValue(film)){
            throw new NewFilmException(film.getName());
        }
        films.put(film.hashCode(), film);
    }

}
