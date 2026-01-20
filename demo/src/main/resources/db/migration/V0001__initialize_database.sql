CREATE DATABASE IF NOT EXISTS bank;

USE bank;

CREATE TABLE IF NOT EXISTS clients(
                         client_id   BIGSERIAL PRIMARY KEY,
                         account VARCHAR(5) NOT NULL UNIQUE,
                         name        VARCHAR(150) NOT NULL,
                         cpf         CHAR(11) NOT NULL UNIQUE,
                         balance     NUMERIC(15,2) NOT NULL DEFAULT 0.00,
                         status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                         created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
                         updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);