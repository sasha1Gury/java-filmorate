package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

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
}