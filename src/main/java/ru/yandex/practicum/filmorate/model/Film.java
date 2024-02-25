package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Integer rate;
    private MPA mpa;
    private List<Genre> genres;
    /*private Set<Long> likes;

    public void addLike(long id) {
        likes.add(id);
    }

    public void deleteLike(long id) {
        likes.remove(id);
    }

    public int getLikeCount() {
        return likes.size();
    }*/

    public void initializeNullFields() {
        rate = (rate == null) ? 0 : rate;
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