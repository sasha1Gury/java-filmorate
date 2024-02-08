package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Film.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @EqualsAndHashCode.Exclude private long id;
    @NotBlank
    private String name;
    @Size(min = 1, max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private int duration;
    private Set<Long> likes;

    public void addLike(long id) {
        likes.add(id);
    }

    public void deleteLike(long id) {
        likes.remove(id);
    }

    public int getLikeCount() {
        return likes.size();
    }
}