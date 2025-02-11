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
import demo.domain.exception.NullException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.dto.request.UpdateRequest;
import demo.resources.dao.UserDao;
import demo.resources.database.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findById(Integer id) throws NotFoundException, NullException {
		 Optional<UserDao> optionalUser = userRepository.findById(id);
		 return toEntity(optionalUser.get());
	}
	
	@Override
	public UserEntity changeActivityUser(UserEntity user) throws NullException{
		user.setActive(!user.getActive());
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity createUser(UserEntity userEntity) throws InvalidBalanceException {
		UserDao userDao = toDao(userEntity);
		UserDao saveDaoUser = userRepository.save(userDao);
		return saveDaoUser.toEntity();
	}
	
	@Override
	public UserEntity deletUser(UserEntity userEntity) {
			userRepository.deleteById(userEntity.getId());
			return userEntity;
	}
	
	@Override
	public UserEntity updateUser(UserEntity userEntity, UpdateRequest updateRequest) throws InvalidNameException, NotFoundException, NullException {

		if(updateRequest.getAccessKey() != null) {
			userEntity.setAcessKey(updateRequest.getAccessKey());
		}
		if(updateRequest.getActive() != null){
			userEntity.setActive(updateRequest.getActive());
		}
		if(updateRequest.getName() != null){
			userEntity.setName(updateRequest.getName());
		};
		userEntity.setUpdated_at(LocalDateTime.now());
		userRepository.save(entityToDao(userEntity));
		return userEntity;
	}
	
	@Override
	public UserEntity withdrawById(UserEntity user, Integer value) throws NullException {
		user.setBalance(user.getBalance() - value);
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity depositById(UserEntity user, Integer value) throws NullException {
		user.setBalance(user.getBalance() + value);
		userRepository.save(entityToDao(user));
		return user;
	}
	
	@Override
	public UserEntity transferToId(Integer id, String accessKey, Integer receptorId, Integer value) throws NotFoundException, NullException {
		UserEntity user = findById(id);
		UserEntity receptor = findById(receptorId);
		user.setBalance(user.getBalance()-value);
		receptor.setBalance(receptor.getBalance()+value);
		userRepository.save(entityToDao(user));
		userRepository.save(entityToDao(receptor));
		System.out.println("Transfer" + "Name: "+user.getName() +"Transfer to: "+ receptor.getName()+"Amount: "+value+ "Your balance is:"+user.getBalance());;
		return user;
	}

	
	private UserDao toDao(UserEntity userEntity) {
		UserDao userDao = new UserDao();
		userDao.setId(userEntity.getId());
		userDao.setAccessKey(userEntity.getAcessKey());
		userDao.setActive(userEntity.getActive());
		userDao.setName(userEntity.getName());
		userDao.setBalance(userEntity.getBalance());
		userDao.setCreated_at(LocalDateTime.now());
		userDao.setUpdated_at(LocalDateTime.now());
		return userDao;
	}
	
	private UserEntity toEntity(UserDao userDao) throws NullException {
		if(userDao == null) {
			throw new NullException("User DAO is Null",ErrorType.NULL);
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

	private UserDao entityToDao (UserEntity userEntity) throws NullException {
		if(userEntity == null) {
			throw new NullException("User Entity is Null",ErrorType.NULL);
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
