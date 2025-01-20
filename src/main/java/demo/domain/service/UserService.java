package demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.UserDto;
import demo.resources.database.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserGateway userGateway;
	
	public UserEntity getUserById(Integer id) throws NotFoundException {
		UserEntity user = userGateway.findById(id);
		if(user == null) {
			throw new NotFoundException("Not Found USER for id:"+id, ErrorType.NOT_FOUND);
		}
		return user;
	}

	public UserEntity changeActivityUser(Integer id, Boolean active) throws NotFoundException {
		return userGateway.changeActivityUser(id,active);
	}

	public UserEntity createUser(UserDto userDto) throws InvalidBalanceException {
		return userGateway.createUser(userDto);
	}

	public UserEntity updateUser(Integer id, UserDto userDto) throws InvalidNameException, NotFoundException {
		return userGateway.updateUser(id, userDto);
	}

	public ResponseEntity<Void> deletUser(Integer id) throws NotFoundException {
		if(userGateway.deletUser(id)) {;
			return ResponseEntity.noContent().build();
		}else {
			throw new NotFoundException("The user not founded",ErrorType.NOT_FOUND);
		}
	}
	
}
