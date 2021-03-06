CREATE SCHEMA IF NOT EXISTS TRAINING;
SET SCHEMA TRAINING;

CREATE TABLE IF NOT EXISTS TRAINING.USER
(
    id         NUMBER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login      varchar NOT NULL,
    password   varchar NOT NULL,
    email      varchar NOT NULL UNIQUE,
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    birthday   DATE    NOT NULL,
    role_id    NUMBER  NOT NULL
);

CREATE TABLE IF NOT EXISTS TRAINING.ROLE
(
    id   NUMBER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR NOT NULL
);

ALTER TABLE IF EXISTS TRAINING.USER
    ADD CONSTRAINT IF NOT EXISTS FKUser FOREIGN KEY (role_id) REFERENCES TRAINING.ROLE (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

INSERT INTO TRAINING.ROLE
VALUES (DEFAULT, 'admin'),
       (DEFAULT, 'user');

INSERT INTO TRAINING.USER
VALUES (DEFAULT, 'user1', '1resu', 'user1@example.mail.com', 'user', 'user', '2000-10-20', 2),
       (DEFAULT, 'user2', '2resu', 'user2@example.mail.com', 'user', 'user', '2000-10-20', 2),
       (DEFAULT, 'user3', '3resu', 'user3@example.mail.com', 'user', 'user', '2000-10-20', 2),
       (DEFAULT, 'admin', 'admin', 'admin@example.mail.com', 'admin', 'admin', '2000-10-20', 1);