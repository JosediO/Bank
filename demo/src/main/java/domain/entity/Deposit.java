package domain.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name="deposits")
public class Deposit {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long depositId;
        private String account;
        private BigDecimal amount;
        private LocalDateTime createdAt;
}
