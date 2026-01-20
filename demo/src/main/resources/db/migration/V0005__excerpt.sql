CREATE DATABASE IF NOT EXISTS bank;

USE bank;

CREATE TABLE IF NOT EXISTS excerpt (
    statement_id  BIGSERIAL PRIMARY KEY,
    account       VARCHAR(5) NOT NULL,
    operation     VARCHAR(20) NOT NULL,
    amount        NUMERIC(15,2) NOT NULL,
    balance_after NUMERIC(15,2) NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    );
