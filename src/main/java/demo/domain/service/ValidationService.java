package demo.domain.service;

import org.springframework.stereotype.Service;

import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidNameException;

@Service
public class ValidationService {
	
	private final Integer MIN_AGE = 17;
	
	public String validationName(String name) throws InvalidNameException {
		
		if(name.length() < 3 || name.length() > 50) {
			throw new InvalidNameException("The first name need 3 or more letters, and all name needs contains max 50 caracters.",ErrorType.INVALID_DATA_FORMAT);
		}
		return name;
	}
}
