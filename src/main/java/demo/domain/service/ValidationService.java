package demo.domain.service;

import org.springframework.stereotype.Service;

import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidAccessKeyException;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotActiveException;

@Service
public class ValidationService {
	
	private final Integer MIN_AGE = 17;
	
	public String validationName(String name) throws InvalidNameException {	
		if(name.length() < 3 || name.length() > 50) {
			throw new InvalidNameException("The first name need 3 or more letters, and all name needs contains max 50 caracters.",ErrorType.INVALID_DATA_FORMAT);
		}
		return name;
	}
	
	public void validationAccessKey(String accesskey, String accesskeyUser) throws InvalidAccessKeyException {
		if(accesskey == null || !accesskeyUser.equals(accesskey)) {
			throw new InvalidAccessKeyException("The AccessKey is not valid, try again",ErrorType.INVALID_KEY);
		}
	}
	
	public void validationPositiveBalance(Integer balance) throws InvalidBalanceException {
		if(balance < 0) {
			throw new InvalidBalanceException("Insert a positive balance.",ErrorType.INVALID_VALUE_FORMAT);
		}
	}
	
	public void validationValidBalance(Integer balance, Integer value) throws InvalidBalanceException {
		if(balance <= 0 || value >= balance) {
			throw new InvalidBalanceException("This account not have balance.", ErrorType.INVALID_VALUE);
		}
	}
	
	public void validationStatus(Boolean status) throws NotActiveException {
		if( status != true) {
			throw new NotActiveException("Dont create user inactive, please select ACTIVE mode.",ErrorType.INVALID_VALUE);
		}
	}
	
	public void validationCreateUser(String name, Integer balance, Boolean status) throws InvalidNameException, NotActiveException, InvalidBalanceException {
		validationName(name);
		validationPositiveBalance(balance);
		validationStatus(status);
	}
}
