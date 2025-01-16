package demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.UserDto;
import demo.resources.database.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserGateway userGateway;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserById(Integer id) throws NotFoundException {
		UserEntity user = userGateway.findById(id);
		if(user == null) {
			throw new NotFoundException("Not Found USER for id:"+id, ErrorType.NOT_FOUND);
		}
		return user;
	}

	public UserEntity changeActivityUser(Integer id, Boolean active) throws NotFoundException {
		UserEntity user = getUserById(id);
			/*if(user.getActive() == true) {
				user.setActive(false);
			}else if(user.getActive()== false) {
				user.setActive(true);*/
			//}
		user.setActive(!user.getActive());
		
		return userGateway.changeActivityUser(id,active);
	}

	public UserEntity createUser(UserDto userDto) {
		return userGateway.createUser(userDto);
	}

	public UserEntity updateUser(Integer id, UserDto userDto) throws InvalidNameException {
		return userGateway.updateUser(id, userDto);
	}
	
}
