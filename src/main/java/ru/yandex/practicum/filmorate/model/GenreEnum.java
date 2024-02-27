package ru.yandex.practicum.filmorate.model;

public enum GenreEnum {
    COMEDY("Комедия"),
    DRAMA("Драма"),
    ANIMATION("Мультфильм"),
    THRILLER("Триллер"),
    DOCUMENTARY("Документальный"),
    ACTION("Боевик"),
    ADVENTURE("Приключения"),
    CRIME("Криминал"),
    HORROR("Ужасы"),
    FANTASY("Фэнтези"),
    ROMANCE("Романтика"),
    FAMILY("Семейный"),
    WAR("Военный"),
    MUSIC("Музыка"),
    BIOGRAPHY("Биография"),
    SCI_FI("Научная фантастика"),
    WESTERN("Вестерн"),
    POST_APOCALYPTIC("Постапокалипсис");

    private final String displayName;

    GenreEnum(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static GenreEnum valueOfDisplayName(String displayName) {
        for (GenreEnum rating : GenreEnum.values()) {
            if (rating.displayName.equals(displayName)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("No enum constant with displayName " + displayName);
    }
}
