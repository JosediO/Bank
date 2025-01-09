package demo.resources.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.domain.entity.UserEntity;
import demo.domain.gateway.UserGateway;
import demo.domain.web.UserDto;
import demo.resources.dao.UserDao;
import demo.resources.database.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findById(Integer id) {
		return toEntity(userRepository.findById(id).orElse(null));
	}
	
	@Override
	public UserEntity changeActivityUser(Integer id) {
		return null;
	}
	
	private UserDao toDao(UserDto userDto) {
		UserDao userDao = new UserDao();
		userDao.setId(userDto.getId());
		userDao.setAcessKey(userDto.getAcessKey());
		userDao.setActive(userDto.getActive());
		userDao.setName(userDto.getName());
		userDao.setBalance(userDto.getBalance());
		userDao.setCreated_at(LocalDateTime.now());
		userDao.setUpdated_at(LocalDateTime.now());
		return userDao;
	}
	
	private UserEntity toEntity(UserDao userDao) {
		UserEntity entityUser = new UserEntity();
		entityUser.setId(userDao.getId());
		entityUser.setAcessKey(userDao.getAcessKey());
		entityUser.setActive(userDao.getActive());
		entityUser.setName(userDao.getName());
		entityUser.setBalance(userDao.getBalance());
		return entityUser;
	}

}
