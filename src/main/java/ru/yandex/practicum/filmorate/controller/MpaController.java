package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.MpaDbService;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@Slf4j
public class MpaController {

    private final MpaDbService mpaDbService;

    public MpaController(MpaDbService mpaDbService) {
        this.mpaDbService = mpaDbService;
    }

    @GetMapping
    public List<Film.MPA> getAllMPAs() {
        return mpaDbService.getAllMPAs();
    }

    @GetMapping("/{id}")
    public Film.MPA getMPAById(@PathVariable("id") long id) {
        return mpaDbService.getMPAById(id);
    }
}
