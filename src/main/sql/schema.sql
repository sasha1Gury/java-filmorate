CREATE TABLE IF NOT EXISTS  "likes"(
    "likes_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "film_id" BIGINT NOT NULL,
    "user_id" BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS "film_genre"(
    "film_genre_id" BIGINT NOT NULL,
    "film_id" BIGINT NOT NULL,
    "genre_id" BIGINT NOT NULL
);
ALTER TABLE
    "film_genre" ADD PRIMARY KEY("film_genre_id");
CREATE TABLE IF NOT EXISTS "Genre"(
    "genre_id" BIGINT NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Genre" ADD PRIMARY KEY("genre_id");
CREATE TABLE IF NOT EXISTS "Film"(
    "film_id" BIGINT NOT NULL,
    "name" TEXT NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "release_date" DATE NOT NULL,
    "duration" INTEGER NOT NULL,
    "rating_id" BIGINT NOT NULL
);
ALTER TABLE
    "Film" ADD PRIMARY KEY("film_id");
CREATE TABLE IF NOT EXISTS "rating"(
    "rating_id" BIGINT NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "rating" ADD PRIMARY KEY("rating_id");
CREATE TABLE IF NOT EXISTS "Users"(
    "user_id" BIGINT NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "login" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "birthday" DATE NOT NULL
);
ALTER TABLE
    "Users" ADD PRIMARY KEY("user_id");
CREATE TABLE IF NOT EXISTS "friends"(
    "friends_id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "user1_id" BIGINT NOT NULL,
    "user2_id" BIGINT NOT NULL,
    "status" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "film_genre" ADD CONSTRAINT "film_genre_film_id_foreign" FOREIGN KEY("film_id") REFERENCES "Film"("film_id");
ALTER TABLE
    "likes" ADD CONSTRAINT "likes_film_id_foreign" FOREIGN KEY("film_id") REFERENCES "Film"("film_id");
ALTER TABLE
    "friends" ADD CONSTRAINT "friends_user2_id_foreign" FOREIGN KEY("user2_id") REFERENCES "Users"("user_id");
ALTER TABLE
    "friends" ADD CONSTRAINT "friends_user1_id_foreign" FOREIGN KEY("user1_id") REFERENCES "Users"("user_id");
ALTER TABLE
    "film_genre" ADD CONSTRAINT "film_genre_genre_id_foreign" FOREIGN KEY("genre_id") REFERENCES "Genre"("genre_id");
ALTER TABLE
    "likes" ADD CONSTRAINT "likes_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "Users"("user_id");
ALTER TABLE
    "Film" ADD CONSTRAINT "film_rating_id_foreign" FOREIGN KEY("rating_id") REFERENCES "rating"("rating_id");