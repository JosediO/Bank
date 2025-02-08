package demo.domain.service;


import demo.domain.entity.Transfer;
import org.apache.catalina.User;
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
import demo.domain.exception.NullException;
import demo.domain.gateway.UserGateway;
import demo.domain.web.dto.request.UpdateRequest;
import demo.domain.web.dto.response.UserDto;

@Service
public class UserService {

	@Autowired
	private UserGateway userGateway;
	
	@Autowired
	private ValidationService validationService;
	
	public UserEntity getUserById(Integer id) throws NotFoundException, NullException {
		UserEntity user = userGateway.findById(id);
		if(user == null) {
			throw new NotFoundException("Not Found USER for id:"+id, ErrorType.NOT_FOUND);
		}
		return user;
	}

	public UserEntity changeActivityUser(Integer id, Boolean active) throws NotFoundException, NotActiveException, NullException {
		UserEntity user = userGateway.findById(id);
		validationService.validationStatus(active);
		return userGateway.changeActivityUser(user);
	}

	public UserEntity createUser(UserEntity userEntity) throws InvalidBalanceException, InvalidNameException, NotActiveException {
		validationService.validationCreateUser(userEntity.getName(), userEntity.getBalance(), userEntity.getActive());
		return userGateway.createUser(userEntity);
	}
	
	public UserEntity withdrawById(Integer id, String accessKey, Integer value) throws NotFoundException, InvalidBalanceException, InvalidAccessKeyException, NullException {
		UserEntity user = getUserById(id);
		validationService.validationAccessKey(accessKey, user.getAcessKey());
		validationService.validationValidBalance(user.getBalance(), value);
		validationService.validationPositiveBalance(user.getBalance());
		return userGateway.withdrawById(user, value);
	}
	
	public UserEntity depositById(Integer id, Integer value, String accessKey) throws InvalidBalanceException, NotFoundException, InvalidAccessKeyException, NullException {
		UserEntity user = getUserById(id);
		validationService.validationAccessKey(accessKey, user.getAcessKey());
		validationService.validationPositiveBalance(user.getBalance());
		return userGateway.depositById(user,value);
	}
	
	public UserEntity transferToId(Integer id, Transfer transfer) throws NotFoundException, InvalidAccessKeyException, InvalidBalanceException, NullException {
		UserEntity user = getUserById(id);
		UserEntity receptor = getUserById(transfer.getReceptorId());

		//Validou o que precisava
		validationService.validationAccessKey(transfer.getAccessKey(), user.getAcessKey());
		validationService.validationValidBalance(user.getBalance(), transfer.getValue());

		//Processamento das informacoes
//		user.setBalance(user.getBalance() - transfer.getValue());
//		receptor.setBalance(receptor.getBalance() + transfer.getValue());
		user.subValue(transfer.getValue());
		receptor.addValue(transfer.getValue());

		UserEntity userUpdated = userGateway.update(user);
		userGateway.update(receptor);

		return userUpdated;
	}

	public UserEntity updateUser(Integer id, UpdateRequest updateRequest) throws InvalidNameException, InvalidAccessKeyException, NotFoundException, NotActiveException, NullException {
		UserEntity user = getUserById(id);
		if(updateRequest.getAccessKey()	!= null && updateRequest.getAccessKey().length() != 4 ) {
				throw new InvalidAccessKeyException("The Access Key is invalid!", ErrorType.NOT_UPDATED);
			}
		if(updateRequest.getActive() != null && updateRequest.getActive() == false) {
			throw new NotActiveException("The user status is not ACTIVE", ErrorType.NOT_UPDATED);	
		}
		if(updateRequest.getName()!= null) {
			validationService.validationName(updateRequest.getName());
		}
		return userGateway.updateUser(user, updateRequest);
		}

	public UserEntity deletUser(Integer id) throws NotFoundException, NullException {
		UserEntity user = getUserById(id);
		return userGateway.deletUser(user);
	}



	
}
