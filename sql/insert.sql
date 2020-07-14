CREATE TABLE IF NOT EXISTS CUSTOMER
(
    id         NUMBER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login      varchar NOT NULL,
    password   varchar NOT NULL,
    email      varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    birthday   DATE    NOT NULL,
    role_id    NUMBER  NOT NULL
);

CREATE TABLE IF NOT EXISTS ROLE
(
    id   NUMBER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR NOT NULL
);

ALTER TABLE IF EXISTS CUSTOMER
    ADD CONSTRAINT IF NOT EXISTS FKUser FOREIGN KEY (role_id) REFERENCES ROLE (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

INSERT INTO ROLE
VALUES (DEFAULT, 'admin'),
       (DEFAULT, 'user');

INSERT INTO CUSTOMER
VALUES (DEFAULT, 'user', 'user', 'user1@example.mail.com', 'user', 'user', '2000-10-20', 2),
       (DEFAULT, 'user2', '2resu', 'user2@example.mail.com', 'user2', 'user2', '1980-10-20', 2),
       (DEFAULT, 'user3', '3resu', 'user3@example.mail.com', 'user3', 'user3', '1990-10-20', 2),
       (DEFAULT, 'admin', 'admin', 'admin@example.mail.com', 'admin', 'admin', '2010-10-20', 1);
