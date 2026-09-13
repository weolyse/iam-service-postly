CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(30) NOT NULL UNIQUE,
                       password VARCHAR(80) NOT NULL,
                       email VARCHAR(50) UNIQUE,
                       created TIMESTAMP NOT NULL DEFAULT current_timestamp,
                       updated TIMESTAMP NOT NULL DEFAULT current_timestamp,
                       registration_status VARCHAR(30) NOT NULL,
                       last_login TIMESTAMP,
                       deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    likes INTEGER NOT NULL DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT current_timestamp,
    updated TIMESTAMP NOT NULL DEFAULT current_timestamp,
    deleted BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO users(username, password, email, registration_status, last_login) VALUES
                                      ('username1', 'password1', 'user1@gmail.com', 'ACTIVE', CURRENT_TIMESTAMP),
                                      ('username2', 'password2', 'user2@gmail.com', 'ACTIVE', CURRENT_TIMESTAMP),
                                      ('username3', 'password3', 'user3@gmail.com', 'ACTIVE', CURRENT_TIMESTAMP);

INSERT INTO posts(title, content) VALUES
                                      ('First post', 'This is the content of the first post.'),
                                      ('Second post', 'This is the content of the second post.'),
                                      ('Third post', 'This is the content of the third post.'),
                                      ('Fourth post', 'This is the content of the fourth post.'),
                                      ('Fifth post', 'This is the content of the fifth post.');
