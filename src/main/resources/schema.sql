CREATE TABLE IF NOT EXISTS "rating"(
    "rating_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "Film"(
    "film_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "release_date" DATE NOT NULL,
    "duration" INTEGER NOT NULL,
    "rating_id" BIGINT NOT NULL,
    "rate" INTEGER,
    CONSTRAINT "film_rating_id_foreign" FOREIGN KEY("rating_id") REFERENCES "rating"("rating_id")
);

CREATE TABLE IF NOT EXISTS "Users"(
    "user_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "email" VARCHAR(255) NOT NULL,
    "login" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "birthday" DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS "Genre"(
    "genre_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS  "likes"(
    "likes_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "film_id" BIGINT NOT NULL,
    "user_id" BIGINT NOT NULL,
    CONSTRAINT "likes_film_id_foreign" FOREIGN KEY("film_id") REFERENCES "Film"("film_id"),
    CONSTRAINT "likes_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "Users"("user_id")
);

CREATE TABLE IF NOT EXISTS "film_genre"(
    "film_genre_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "film_id" BIGINT NOT NULL,
    "genre_id" BIGINT NOT NULL,
    CONSTRAINT "film_genre_film_id_foreign" FOREIGN KEY("film_id") REFERENCES "Film"("film_id"),
    CONSTRAINT "film_genre_genre_id_foreign" FOREIGN KEY("genre_id") REFERENCES "Genre"("genre_id")
);

CREATE TABLE IF NOT EXISTS "friends"(
    "friends_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "user1_id" BIGINT NOT NULL,
    "user2_id" BIGINT NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    CONSTRAINT "friends_user2_id_foreign" FOREIGN KEY("user2_id") REFERENCES "Users"("user_id"),
    CONSTRAINT "friends_user1_id_foreign" FOREIGN KEY("user1_id") REFERENCES "Users"("user_id")
);