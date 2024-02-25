package ru.yandex.practicum.filmorate.model;

public enum RatingMpa {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
    private final String displayName;

    RatingMpa(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }

    public static RatingMpa valueOfDisplayName(String displayName) {
        for (RatingMpa rating : RatingMpa.values()) {
            if (rating.displayName.equals(displayName)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("No enum constant with displayName " + displayName);
    }

}