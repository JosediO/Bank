package com.example.demo.domain.service;

import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidationService {

    public void validationAccount(String account) {
        if (account == null) {
            throw new NullException();
        }
        if (account.length() != 5 || !account.matches("^[a-zA-Z0-9]$")) {
            throw new InvalidAccountException();
        }
    }

    public void validationClientName(String name) {
        if (name == null || !name.matches("^[a-zA-Z ]{10,50}$")) {
            throw new InvalidNameException();
        }

    }

    public void validationCpf(String cpf) {
        if (cpf == null || !cpf.matches("^\\d{11}$")) {
            throw new InvalidCpfException();
        }
    }

    public void validationPositiveBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBalanceException();
        }
    }

    public void validationBalanceTransation(BigDecimal balance, BigDecimal amount) {
        if ((balance.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(BigDecimal.ZERO) >= balance.compareTo(BigDecimal.ZERO))) {
            throw new InvalidBalanceException();
        }
    }

    public void validationStatus(ClientStatus status) {
        if(status != ClientStatus.ACTIVE){
            throw new NotActiveException();
    }

}






}
