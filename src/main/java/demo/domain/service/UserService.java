package demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.domain.entity.UserEntity;
import demo.domain.gateway.UserGateway;

@Service
public class UserService {

	@Autowired
	private UserGateway userGateway;
	
	public UserEntity getUserById(Integer id) {
		return userGateway.findById(id);
	}

	public UserEntity changeActivityUser(Integer id) {
		return userGateway.changeActivityUser(id);
	}
	
}
