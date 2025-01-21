package demo.domain.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	private String acessKey;
	private Boolean active;
	private String name;
	private Integer balance;
}
