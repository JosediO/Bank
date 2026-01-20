CREATE DATABASE IF NOT EXISTS bank;

USE bank;

CREATE TABLE IF NOT EXISTS deposits(

                         deposit_id   BIGSERIAL PRIMARY KEY,
                         account VARCHAR(5) NOT NULL,
                         value NUMERIC ( 15,2) NOT NULL,
                         created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
);