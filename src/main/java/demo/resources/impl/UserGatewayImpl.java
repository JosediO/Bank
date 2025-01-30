package demo.resources.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.dto.UserDto;
import demo.domain.web.dto.request.UpdateRequest;
import demo.resources.dao.UserDao;
import demo.resources.database.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findById(Integer id) throws NotFoundException {
		 Optional<UserDao> optionalUser = userRepository.findById(id);
		 if (optionalUser.isEmpty()) {
		        throw new NotFoundException("User not found for ID: " + id, ErrorType.NOT_FOUND);
		    }
		    return toEntity(optionalUser.get());
	}
	
	@Override
	public UserEntity changeActivityUser(UserEntity user, Boolean active){
		user.setActive(!user.getActive());
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity createUser(UserDto userDto) throws InvalidBalanceException {
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
	public UserEntity updateUser(Integer id, UpdateRequest updateRequest) throws InvalidNameException, NotFoundException {
		UserEntity user = findById(id);
		if(updateRequest.getAccessKey() != null) {
			user.setAcessKey(updateRequest.getAccessKey());
		}
		if(updateRequest.getActive() != null){
			user.setActive(updateRequest.getActive());
		}
		if(updateRequest.getName() != null){
			user.setName(updateRequest.getName());
		};
		user.setUpdated_at(LocalDateTime.now());
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity withdrawById(UserEntity user, Integer value) {
		user.setBalance(user.getBalance() - value);
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity depositById(UserEntity user, Integer value) {
		user.setBalance(user.getBalance() + value);
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity transferToId(Integer id, String accessKey, Integer receptorId, Integer value) throws NotFoundException {
		UserEntity user = findById(id);
		UserEntity receptor = findById(receptorId);
		user.setBalance(user.getBalance()-value);
		receptor.setBalance(receptor.getBalance()+value);
		userRepository.save(entityToDao(user));
		userRepository.save(entityToDao(receptor));
		System.out.println("Transfer" + "Name: "+user.getName() +"Transfer to: "+ receptor.getName()+"Amount: "+value+ "Your balance is:"+user.getBalance());;
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
		entityUser.setCreated_at(userDao.getCreated_at());
		entityUser.setUpdated_at(LocalDateTime.now());
		return entityUser;
	}

	private UserDao entityToDao (UserEntity userEntity) {
		if(userEntity == null) {
			System.out.println("USER Entity NULL");
		}
		UserDao daoUser = new UserDao();
		daoUser.setId(userEntity.getId());
		daoUser.setAccessKey(userEntity.getAcessKey());
		daoUser.setActive(userEntity.getActive());
		daoUser.setName(userEntity.getName());
		daoUser.setBalance(userEntity.getBalance());
		daoUser.setCreated_at(userEntity.getCreated_at());
		daoUser.setUpdated_at(LocalDateTime.now());
		return daoUser;
	}

}
