package demo.domain.gateway;


import demo.domain.entity.Transfer;
import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.exception.NullException;
import demo.domain.web.dto.request.UpdateRequest;
import org.apache.catalina.User;

public interface UserGateway {

	public UserEntity findById(Integer id) throws NotFoundException, NullException;
	public UserEntity changeActivityUser(UserEntity user) throws NotFoundException, NullException;
	public UserEntity createUser(UserEntity userEntity) throws InvalidBalanceException;
	public UserEntity updateUser(UserEntity userEntity, UpdateRequest updateRequest) throws InvalidNameException, NotFoundException, NullException;
	public UserEntity deletUser(UserEntity userEntity);
	public UserEntity withdrawById(UserEntity user, Integer value) throws NullException;
	public UserEntity depositById(UserEntity user, Integer value) throws NullException;
//	public UserEntity transferToId(Integer id, Transfer transfer) throws NotFoundException, NullException;
	public UserEntity update(UserEntity userEntity);

}
