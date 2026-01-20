CREATE DATABASE IF NOT EXISTS bank;

USE bank;

CREATE TABLE IF NOT EXISTS transfer(

                         transfer_id   BIGSERIAL PRIMARY KEY,
                         from_account VARCHAR(5) NOT NULL,
                        to_account VARCHAR(5) NOT NULL,
                         value NUMERIC ( 15,2) NOT NULL,
                         created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
);