package demo.domain.gateway;


import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.web.UserDto;

public interface UserGateway {

	public UserEntity findById(Integer id) throws NotFoundException;
	public UserEntity changeActivityUser(Integer id, Boolean active) throws NotFoundException;
	public UserEntity createUser(UserDto userDto) throws InvalidBalanceException;
	public UserEntity updateUser(Integer id, UserDto userDto) throws InvalidNameException, NotFoundException;
	public Boolean deletUser(Integer id);

}
