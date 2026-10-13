CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    token VARCHAR(128) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT refresh_token_users_FK FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT unique_refresh_token UNIQUE (id, user_id)
);