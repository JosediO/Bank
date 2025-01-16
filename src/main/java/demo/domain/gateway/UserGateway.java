package demo.domain.gateway;

import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidNameException;
import demo.domain.web.UserDto;

public interface UserGateway {

	public UserEntity findById(Integer id);
	public UserEntity changeActivityUser(Integer id, Boolean active);
	public UserEntity createUser(UserDto userDto);
	public UserEntity updateUser(Integer id, UserDto userDto) throws InvalidNameException;

}
