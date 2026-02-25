package com.example.demo.domain.service;

import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.InvalidAccountException;
import com.example.demo.domain.exceptions.InvalidBalanceException;
import com.example.demo.domain.exceptions.NotActiveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }

    @Test
    void shouldThrowExceptionWhenAccountIsNull() {

        InvalidAccountException ex = assertThrows(
                InvalidAccountException.class,
                () -> validationService.validationAccount(null)
        );

        assertEquals(ErrorType.NULL, ex.getType());
    }

    @Test
    void shouldThrowExceptionWhenAccountFormatIsInvalid() {

        InvalidAccountException ex = assertThrows(
                InvalidAccountException.class,
                () -> validationService.validationAccount("12")
        );

        assertEquals(ErrorType.INVALID_FORMAT, ex.getType());
    }

    @Test
    void shouldNotThrowExceptionWhenAccountIsValid() {
        assertDoesNotThrow(() ->
                validationService.validationAccount("A1B2C")
        );
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNull() {

        InvalidBalanceException ex = assertThrows(
                InvalidBalanceException.class,
                () -> validationService.validationBalanceTransaction(
                        new BigDecimal("1000"),
                        null
                )
        );

        assertEquals(ErrorType.NULL, ex.getType());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {

        InvalidBalanceException ex = assertThrows(
                InvalidBalanceException.class,
                () -> validationService.validationBalanceTransaction(
                        new BigDecimal("1000"),
                        new BigDecimal("-10")
                )
        );

        assertEquals(ErrorType.INVALID_VALUE, ex.getType());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsGreaterThanBalance() {

        InvalidBalanceException ex = assertThrows(
                InvalidBalanceException.class,
                () -> validationService.validationBalanceTransaction(
                        new BigDecimal("500"),
                        new BigDecimal("700")
                )
        );

        assertEquals(ErrorType.INVALID_VALUE, ex.getType());
    }

    @Test
    void shouldNotThrowExceptionWhenTransactionIsValid() {
        assertDoesNotThrow(() ->
                validationService.validationBalanceTransaction(
                        new BigDecimal("1000"),
                        new BigDecimal("200")
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenStatusIsNotActive() {

        NotActiveException ex = assertThrows(
                NotActiveException.class,
                () -> validationService.validationStatus(ClientStatus.BLOCKED)
        );

        assertEquals(ErrorType.INACTIVE, ex.getType());
    }
}
