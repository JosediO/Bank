package demo.resources.dao;

import java.time.LocalDateTime;


import demo.domain.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String acessKey;
	private Boolean active;
	private String name;
	private Integer balance;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public UserEntity toEntity() {
		UserEntity entity = new UserEntity();
		entity.setId(id);
		entity.setAcessKey(acessKey);
		entity.setActive(active);
		entity.setName(name);
		entity.setBalance(balance);
		return entity;
	}
}
