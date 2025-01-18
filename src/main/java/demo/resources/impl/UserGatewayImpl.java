package demo.resources.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.service.ValidationService;
import demo.domain.web.UserDto;
import demo.resources.dao.UserDao;
import demo.resources.database.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValidationService validationService;

	@Override
	public UserEntity findById(Integer id) throws NotFoundException {
		 Optional<UserDao> optionalUser = userRepository.findById(id);
		 if (optionalUser.isEmpty()) {
		        throw new NotFoundException("User not found for ID: " + id, ErrorType.NOT_FOUND);
		    }
		    return toEntity(optionalUser.get());
	}
	
	@Override
	public UserEntity changeActivityUser(Integer id, Boolean active) throws NotFoundException {
		UserEntity user = findById(id);
		return userRepository.save(user);
	}
	
	@Override
	public UserEntity createUser(UserDto userDto) {
		UserDao userDao = toDao(userDto);
		UserDao saveDaoUser = userRepository.save(userDao);
		return saveDaoUser.toEntity();
	}
	
	@Override
	public Boolean deletUser(Integer id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public UserEntity updateUser(Integer id, UserDto userDto) throws InvalidNameException, NotFoundException {
		UserEntity user = findById(id);
		if(userDto.getName() != null) {
			validationService.validationName(userDto.getName());
			user.setName(userDto.getName());
		}
		user.setUpdated_at(LocalDateTime.now());
		
		return user;
	}
	
	
	private UserDao toDao(UserDto userDto) {
		UserDao userDao = new UserDao();
		userDao.setId(userDto.getId());
		userDao.setAccessKey(userDto.getAcessKey());
		userDao.setActive(userDto.getActive());
		userDao.setName(userDto.getName());
		userDao.setBalance(userDto.getBalance());
		userDao.setCreated_at(LocalDateTime.now());
		userDao.setUpdated_at(LocalDateTime.now());
		return userDao;
	}
	
	private UserEntity toEntity(UserDao userDao) {
		if(userDao == null) {
			System.out.println("USER DAO NULL");
		}
		UserEntity entityUser = new UserEntity();
		entityUser.setId(userDao.getId());
		entityUser.setAcessKey(userDao.getAccessKey());
		entityUser.setActive(userDao.getActive());
		entityUser.setName(userDao.getName());
		entityUser.setBalance(userDao.getBalance());
		return entityUser;
	}

}
