package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    @EqualsAndHashCode.Exclude private long id;
    @Email
    private String email;
    @NonNull
    @NotBlank
    private String login;
    private String name;
    @PastOrPresent
    private LocalDate birthday;
    private Set<Long> friends;

    public void addFriend(Long id) {
        friends.add(id);
    }

    public void deleteFriend(Long id) {
        friends.remove(id);
    }
}