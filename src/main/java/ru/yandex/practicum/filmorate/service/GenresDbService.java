package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.GenresDbStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@Service
public class GenresDbService {

    private final GenresDbStorage genresDbStorage;

    public GenresDbService(GenresDbStorage genresDbStorage) {
        this.genresDbStorage = genresDbStorage;
    }

    public List<Film.Genre> getAllGenres() {
        return genresDbStorage.getAllGenres();
    }

    public Film.Genre getGenreById(long id) {
        return genresDbStorage.getGenreById(id);
    }
}
