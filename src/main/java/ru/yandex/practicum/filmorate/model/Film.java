package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @EqualsAndHashCode.Exclude private int id;
    @NotBlank
    private String name;
    @Size(min = 1, max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private int duration;
}