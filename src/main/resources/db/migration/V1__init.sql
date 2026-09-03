CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT current_timestamp,
    likes INTEGER NOT NULL DEFAULT 0,
    UNIQUE (title)
);

INSERT INTO posts(title, content) VALUES
                                      ('First post', 'This is the content of the first post.'),
                                      ('Second post', 'This is the content of the second post.');
