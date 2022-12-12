CREATE ROLE book_rental WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    REPLICATION
    CONNECTION LIMIT -1
    PASSWORD '12345678';

CREATE TABLE book
(
    id           SERIAL PRIMARY KEY,
    isbn         VARCHAR(20)  NOT NULL,
    title        VARCHAR(100) NOT NULL,
    author       VARCHAR(100) NOT NULL,
    publisher    VARCHAR(100) NOT NULL,
    page_number  SMALLINT     NOT NULL,
    genre        VARCHAR(100) NOT NULL,
    rented_by    VARCHAR(100),
    rented_until DATE,
    is_rented    BOOLEAN      NOT NULL
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE role
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
);

CREATE TABLE user_role
(
    id      SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL REFERENCES users (id),
    role_id SERIAL NOT NULL REFERENCES role (id)
);

INSERT INTO users (username, password)
VALUES ('admin', '$2a$12$rehlX.tTkJeeEC/FMHHXAOhEbY/gTGRAdlzKkzfapPgynvqDptWOS');   -- password
INSERT INTO users (username, password)
VALUES ('user', '$2a$12$rehlX.tTkJeeEC/FMHHXAOhEbY/gTGRAdlzKkzfapPgynvqDptWOS');    -- password

INSERT INTO role (role_name)
VALUES ('USER');
INSERT INTO role (role_name)
VALUES ('ADMIN');

INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'admin') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'ADMIN') b;
INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'admin') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'USER') b;
INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'user') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'USER') b;
