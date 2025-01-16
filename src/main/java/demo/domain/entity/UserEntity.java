package demo.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	

	private Integer id;
	private String acessKey;
	private Boolean active;
	private String name;
	private Integer balance;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

}
