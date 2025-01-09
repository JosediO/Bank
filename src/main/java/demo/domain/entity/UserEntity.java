package demo.domain.entity;

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
	
	@Id
	@GeneratedValue
	private Integer id;
	private String acessKey;
	private Boolean active;
	private String name;
	private Integer balance;

}
