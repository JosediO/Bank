package demo.domain.service;

import org.springframework.stereotype.Service;

import demo.domain.enums.ErrorType;
import demo.domain.exception.InvalidNameException;

@Service
public class ValidationService {
	
	private final Integer MIN_AGE = 17;
	
	public String validationName(String name) throws InvalidNameException {
		if(!name.matches
				("^[A-Za-z]{3,}(?:\\s+[A-Za-z]{3,}){3,49}$")){
			throw new InvalidNameException("The name is invalid, insert a valid name.", ErrorType.INVALID_DATA_FORMAT);
		}
		return name;	
	}

}
