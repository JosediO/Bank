package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ValidationService {

    void validationAccount(String account) {
        if (account == null) {
            //log.info("Account creation failed. Please check that you have filled in the fields and try again.");
            throw new InvalidAccountException("Account creation failed. Please check that you have filled in the fields and try again.", ErrorType.NULL);
        }
        if (account.length() != 5 || !account.matches("^[a-zA-Z0-9]{5}$")) {
            log.info("The account does not contain 5 characters or does not contain alphanumeric characters and numbers.");
            throw new InvalidAccountException("The account does not contain 5 characters or does not contain alphanumeric characters and numbers.",ErrorType.INVALID_FORMAT);
        }
    }

    private void validationClientName(String name) {
        if (name == null || !name.matches("^[a-zA-Z ]{10,50}$")) {
            log.info("Account name need minimum 10 and 50 maximum characters");
            throw new InvalidNameException("Account name need minimum 10 and 50 maximum characters",ErrorType.INVALID_FORMAT);
        }

    }

    private void validationCpf(String cpf) {
        if (cpf == null || !cpf.matches("^\\d{11}$")) {
            log.info("Cpf account need 11 numbers");
            throw new InvalidCpfException("Cpf account need 11 numbers",ErrorType.INVALID_FORMAT);
        }
    }

    private void validationPositiveBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            log.info("The balance is negative!");
            throw new InvalidBalanceException("The balance is negative!",ErrorType.INVALID_VALUE);
        }
    }

    void validationBalanceTransaction(BigDecimal balance, BigDecimal amount) {
        if(amount == null){
            log.info("Transaction amount cannot be null.");
            throw new InvalidBalanceException("Transaction amount cannot be null.",ErrorType.NULL);
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.info("Transaction amount must be greater than zero.");
            throw new InvalidBalanceException("Transaction amount must be greater than zero.",ErrorType.INVALID_VALUE);
        }
        if (amount.compareTo(balance) > 0) {
            log.info("Insufficient funds, please enter an amount compatible with your balance.");
            throw new InvalidBalanceException("Insufficient funds, please enter an amount compatible with your balance.",ErrorType.INVALID_VALUE);
        }
    }

    void validationStatus(ClientStatus status) {
        if(status != ClientStatus.ACTIVE){
            log.info("The status is inactive or blocked!");
            throw new NotActiveException("The status is inactive or blocked!",ErrorType.INACTIVE);
        }
    }

    public void validationCreateClient(Client client){
            validationAccount(client.getAccount());
            validationClientName(client.getName());
            validationCpf(client.getCpf());
            validationPositiveBalance(client.getBalance());
            validationStatus(client.getStatus());
        }

}