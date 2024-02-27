package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.GenresDbService;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Slf4j
public class GenresController {

    private final GenresDbService genresDbService;

    public GenresController(GenresDbService genresDbService) {
        this.genresDbService = genresDbService;
    }

    @GetMapping
    public List<Film.Genre> getAllGenres() {
        return genresDbService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Film.Genre getGenreById(@PathVariable("id") long id) {
        return genresDbService.getGenreById(id);
    }

}
