CREATE DATABASE IF NOT EXISTS bankDatabase;

USE BankDatabase;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acessKey VARCHAR(4),
    active BOOLEAN,
    nome VARCHAR(50) NOT NULL,
	balance INT,
    created_at DATETIME,
    updated_at DATETIME
);
