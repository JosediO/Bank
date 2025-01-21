package demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidAccessKeyException;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.dto.UserDto;
import demo.domain.web.dto.request.WithdrawalRequest;
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
	
	public UserEntity withdrawById(Integer id, String accessKey, Integer value) throws NotFoundException, InvalidBalanceException, InvalidAccessKeyException {
		UserEntity user = getUserById(id);
		if(!user.getAcessKey().equals(accessKey)){
			throw new InvalidAccessKeyException("The Access Key, is not equal, try again!", ErrorType.INVALID_KEY);
			}
		if(user.getBalance() <=0 || value > user.getBalance()) {
			throw new InvalidBalanceException("This account not have balance.", ErrorType.INVALID_VALUE);
		} 
		if(value <=0) {
			throw new InvalidBalanceException("The value for withdraw is invalid.", ErrorType.INVALID_VALUE_FORMAT);
		}
		return userGateway.withdrawById(id,accessKey,value);
	}
	
	public UserEntity depositById(Integer id, Integer value) throws InvalidBalanceException, NotFoundException {
		UserEntity user = getUserById(id);
		if(value <=0) {
			throw new InvalidBalanceException("The value for deposit is invalid.", ErrorType.INVALID_VALUE);
		}
		return userGateway.depositById(id,value);
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
