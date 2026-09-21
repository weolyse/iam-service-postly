CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    user_system_role VARCHAR(30) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_by VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles(
    user_id BIGINT NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name, user_system_role, created_by) VALUES
    ('SUPER_ADMIN', 'SUPER_ADMIN', 'SUPER_ADMIN'),
    ('ADMIN', 'ADMIN', 'SUPER_ADMIN'),
    ('USER', 'USER', 'SUPER_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3);
