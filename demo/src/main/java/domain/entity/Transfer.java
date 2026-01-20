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
@Table(name="transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long transferId;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
