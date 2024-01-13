package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
public class Film {
    private int id;
    @NotBlank
    private String name;
    @Max(200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private Duration duration;
}