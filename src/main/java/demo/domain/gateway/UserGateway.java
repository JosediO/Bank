package demo.domain.gateway;

import demo.domain.entity.UserEntity;

public interface UserGateway {

	public UserEntity findById(Integer id);
	public UserEntity changeActivityUser(Integer id);


}
