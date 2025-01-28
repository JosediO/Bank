package demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.domain.entity.UserEntity;
import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidAccessKeyException;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotActiveException;
import demo.domain.exception.NotFoundException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.dto.UserDto;
import demo.domain.web.dto.request.UpdateRequest;


@Service
public class UserService {

	@Autowired
	private UserGateway userGateway;
	
	@Autowired
	private ValidationService validationService;
	
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

	public UserEntity createUser(UserDto userDto) throws InvalidBalanceException, InvalidNameException, NotActiveException {
			validationService.validationName(userDto.getName());
		if(userDto.getBalance() < 0) {
			throw new InvalidBalanceException("Insert a positive balance.",ErrorType.INVALID_VALUE_FORMAT);
		}
		if(userDto.getActive() == false) {
			throw new NotActiveException("Dont create user inactive, please select ACTIVE mode.",ErrorType.INVALID_VALUE);
		}
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
	
	public UserEntity transferToId(Integer id, String accessKey, Integer receptorId, Integer value) throws NotFoundException, InvalidAccessKeyException, InvalidBalanceException {
		UserEntity user = getUserById(id);
		UserEntity receptor = getUserById(receptorId);
		if(!user.getAcessKey().equals(accessKey)){
			throw new InvalidAccessKeyException("The Access Key, is not equal, try again!", ErrorType.INVALID_KEY);
		}
		if(value <=0 || user.getBalance() < value) {
			throw new InvalidBalanceException("The account no have value amount",ErrorType.INVALID_VALUE);
		}
		return userGateway.transferToId(id,accessKey,receptorId,value);
	}

	public UserEntity updateUser(Integer id, UpdateRequest updateRequest) throws InvalidNameException, InvalidAccessKeyException, NotFoundException, NotActiveException {
		UserEntity user = getUserById(id);
		if(updateRequest.getAccessKey()	!= null) {	
			if(updateRequest.getAccessKey().length() != 4) {
				throw new InvalidAccessKeyException("The Access Key is invalid!", ErrorType.NOT_UPDATED);
			}
		}
		if(updateRequest.getActive() != null) {
			if(updateRequest.getActive() == false) {
				throw new NotActiveException("The user status is not ACTIVE", ErrorType.NOT_UPDATED);	
			}
		}
		if(updateRequest.getName()!= null) {
			validationService.validationName(updateRequest.getName());
		}
		return userGateway.updateUser(id, updateRequest);
		}

	public ResponseEntity<Void> deletUser(Integer id) throws NotFoundException {
		if(userGateway.deletUser(id)) {;
			return ResponseEntity.noContent().build();
		}else {
			throw new NotFoundException("The user not founded",ErrorType.NOT_FOUND);
		}
	}



	
}
