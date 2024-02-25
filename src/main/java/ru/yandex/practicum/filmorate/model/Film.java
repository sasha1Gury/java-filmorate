package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

/**
 * Film.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @EqualsAndHashCode.Exclude
    private long id;
    @NotBlank
    @NotBlank
    private String name;
    @Size(min = 1, max = 200)
    @Size(min = 1, max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    @Positive
    private int duration;
    private Integer rate;
    private MPA mpa;
    private Set<Genre> genres;

    public void setGenres(LinkedHashSet<Genre> genres) {
        this.genres = genres;
    }

    public void initializeNullFields() {
        rate = (rate == null) ? 0 : rate;
        genres = (genres == null) ? new HashSet<>() : genres;
    }

    @Data
    @AllArgsConstructor
    public static class MPA {
        private int id;
        private String name;

        public MPA() {

        }

        public MPA(int id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Genre {
        private long id;
        private String name;

        public Genre(int id) {
            this.id = id;
        }
    }

    public List<Long> extractGenreIds() {
        List<Long> genreIds = new ArrayList<>();
        for (Genre genre : genres) {
            genreIds.add(genre.getId());
        }
        return genreIds;
    }
}