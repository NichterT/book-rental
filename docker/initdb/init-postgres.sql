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
VALUES ('librarian', '$2a$12$rehlX.tTkJeeEC/FMHHXAOhEbY/gTGRAdlzKkzfapPgynvqDptWOS'); -- password
INSERT INTO users (username, password)
VALUES ('customer', '$2a$12$rehlX.tTkJeeEC/FMHHXAOhEbY/gTGRAdlzKkzfapPgynvqDptWOS'); -- password

INSERT INTO role (role_name)
VALUES ('CUSTOMER');
INSERT INTO role (role_name)
VALUES ('LIBRARIAN');

INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'librarian') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'LIBRARIAN') b;
INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'librarian') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'CUSTOMER') b;
INSERT INTO user_role (user_id, role_id)
SELECT *
FROM (SELECT u.id FROM users u WHERE u.username = 'customer') a,
     (SELECT r.id FROM role r WHERE r.role_name = 'CUSTOMER') b;

INSERT INTO book (isbn, title, author, publisher, page_number, genre, rented_by, rented_until, is_rented)
VALUES (9789634477822, 'Churchill I.-II. - Kéz a kézben a sorssal', 'Andrew Roberts', 'ALEXANDRA KÖNYVESHÁZ KFT.', 1136,
        'HISTORY', NULL, NULL, FALSE);

INSERT INTO book (isbn, title, author, publisher, page_number, genre, rented_by, rented_until, is_rented)
VALUES (9789633996751, 'A Court of Thorns and Roses', 'Sarah J. Maas', 'KÖNYVMOLYKÉPZŐ KIADÓ', 512,
        'FANTASY', NULL, NULL, FALSE);

INSERT INTO book (isbn, title, author, publisher, page_number, genre, rented_by, rented_until, is_rented)
VALUES (9789634059165, 'A ragyogás', 'Stephen King', 'Európa Kiadó', 440,
        'HORROR', NULL, NULL, FALSE);

INSERT INTO book (isbn, title, author, publisher, page_number, genre, rented_by, rented_until, is_rented)
VALUES (9789632885810, 'Állj ki magadért! - Hogyan lehetsz erős és magabiztos?', 'Gershen Kaufman - Lev Raphael',
        'HARMAT KIADÓI ALAPITVÁNY', 184,
        'CHILDREN', NULL, NULL, FALSE);