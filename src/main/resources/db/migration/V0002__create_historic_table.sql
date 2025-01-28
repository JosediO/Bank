CREATE DATABASE IF NOT EXISTS bankDatabase;

USE BankDatabase;

CREATE TABLE historic (
    idTransaction INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    name VARCHAR(50) NOT NULL,
	balance INT NOT NULL,
    valueTransaction INT NOT NULL,
    service ENUM('DEPOSIT', 'TRANSFER', 'WITHDRAW'),
    toId INT,
    receptor VARCHAR(50),
    date DATETIME NOT NULL
);
