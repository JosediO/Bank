package demo.domain.gateway;


import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.web.dto.UserDto;
import demo.domain.web.dto.request.UpdateRequest;

public interface UserGateway {

	public UserEntity findById(Integer id) throws NotFoundException;
	public UserEntity changeActivityUser(UserEntity user, Boolean active) throws NotFoundException;
	public UserEntity createUser(UserDto userDto) throws InvalidBalanceException;
	public UserEntity updateUser(Integer id, UpdateRequest updateRequest) throws InvalidNameException, NotFoundException;
	public Boolean deletUser(Integer id);
	public UserEntity withdrawById(UserEntity user, Integer value);
	public UserEntity depositById(UserEntity user, Integer value);
	public UserEntity transferToId(Integer id, String accessKey, Integer receptorId, Integer value) throws NotFoundException;

}
