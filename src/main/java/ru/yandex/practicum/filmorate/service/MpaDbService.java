package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.MpaDbStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@Service
public class MpaDbService {

    private final MpaDbStorage mpaDbStorage;

    public MpaDbService(MpaDbStorage mpaDbStorage) {
        this.mpaDbStorage = mpaDbStorage;
    }

    public List<Film.MPA> getAllMPAs() {
        return mpaDbStorage.getAllMPAs();
    }

    public Film.MPA getMPAById(long id) {
        return mpaDbStorage.getMPAById(id);
    }
}
