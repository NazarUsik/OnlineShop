DROP DATABASE IF EXISTS online_shop_proarena;

create database online_shop_proarena;
create schema shop_schema;
set search_path = shop_schema;


CREATE TABLE CUSTOMER
(
    id         serial  not null primary key unique,
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    email      varchar NOT NULL UNIQUE,
    password   varchar NOT NULL,
    role_id    int     NOT NULL
);

CREATE TABLE ROLE
(
    id   SERIAL  NOT NULL UNIQUE PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE PRODUCT
(
    id          SERIAL  NOT NULL PRIMARY KEY,
    name        VARCHAR NOT NULL UNIQUE,
    price       NUMERIC(8, 2),
    amount      INTEGER,
    sold_number INTEGER
);

CREATE TABLE SALES_ORDER
(
    id         SERIAL  NOT NULL PRIMARY KEY,
    date       DATE    NOT NULL,
    user_id    INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    price      NUMERIC(8, 2),
    quantity   INTEGER,
    total      NUMERIC(8, 2)
);

CREATE TABLE Confirmation_Token
(
    token_id           SERIAL  NOT NULL PRIMARY KEY,
    confirmation_token VARCHAR NOT NULL,
    created_date date,
    user_id INTEGER NOT NULL
);

ALTER TABLE Confirmation_Token
    ADD CONSTRAINT FKConfToken FOREIGN KEY (user_id) REFERENCES CUSTOMER(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE CUSTOMER
    ADD CONSTRAINT FKCustomer FOREIGN KEY (role_id) REFERENCES ROLE (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE SALES_ORDER
    ADD CONSTRAINT FKSales_Order1 FOREIGN KEY (user_id) REFERENCES CUSTOMER (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE SALES_ORDER
    ADD CONSTRAINT FKSales_Order2 FOREIGN KEY (product_id) REFERENCES PRODUCT (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;


INSERT INTO ROLE
VALUES (DEFAULT, 'ADMIN'),
       (DEFAULT, 'CUSTOMER');

INSERT INTO CUSTOMER
VALUES (DEFAULT, 'Nazar', 'Usik', 'usik.example@gmail.com', 'admin50510', 1),
       (DEFAULT, 'User', 'User', 'user.example@gmail.com', 'user12345', 2);

INSERT INTO PRODUCT
VALUES (DEFAULT, 'ACE TENNIS RACKET I', 155.25, 25, 0),
       (DEFAULT, 'ACE TENNIS RACKET II', 155.25, 25, 0),
       (DEFAULT, 'ACE TENNIS BALLS-3 PACK', 155.25, 25, 0),
       (DEFAULT, 'ACE TENNIS BALLS-6 PACK', 155.25, 25, 0),
       (DEFAULT, 'ACE TENNIS NET', 155.25, 25, 0),
       (DEFAULT, 'SP TENNIS RACKET', 155.25, 25, 0),
       (DEFAULT, 'SP JUNIOR RACKET', 155.25, 25, 0),
       (DEFAULT, 'RH: "GUIDE TO TENNIS"', 155.25, 25, 0),
       (DEFAULT, 'SB ENERGY BAR-6 PACK', 155.25, 25, 0),
       (DEFAULT, 'SB VITA SNACK-6 PACK', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL BAT I', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL BAT II', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL, SMALL', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL, LARGE', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL MITT (LH)', 155.25, 25, 0),
       (DEFAULT, 'WIFF SOFTBALL MITT (RH)', 155.25, 25, 0),
       (DEFAULT, 'RH: "GUIDE TO SOFTBALL"', 155.25, 25, 0),
       (DEFAULT, 'DUNK BASKETBALL INDOOR', 155.25, 25, 0),
       (DEFAULT, 'DUNK BASKETBALL OUTDOOR', 155.25, 25, 0),
       (DEFAULT, 'DUNK BASKETBALL PROFESSIONAL', 155.25, 25, 0),
       (DEFAULT, 'DUNK HOOP', 155.25, 25, 0),
       (DEFAULT, 'DUNK HOOP W/FIBERGLASS BOARD', 155.25, 25, 0),
       (DEFAULT, 'DUNK NETS - RAINBOW', 155.25, 25, 0),
       (DEFAULT, 'RH: "GUIDE TO BASKETBALL"', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY BICYCLE HELMET', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY BICYCLE GLOVES', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY WATER BOTTLE', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY BOTTLE CAGE', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY FRAME PUMP', 155.25, 25, 0),
       (DEFAULT, 'YELLOW JERSEY SADDLE PACK', 155.25, 25, 0),
       (DEFAULT, 'RH: "GUIDE TO CYCLING"', 155.25, 25, 0);
