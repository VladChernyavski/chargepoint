-- TODO add creation of all the tables

CREATE TABLE user_table
(
    id           SERIAL PRIMARY KEY,
    email        VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    password     VARCHAR NOT NULL,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    gender       VARCHAR NOT NULL,
    birthday     TIMESTAMP NOT NULL
);
