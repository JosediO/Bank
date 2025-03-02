package demo.resources.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.domain.entity.UserEntity;
import demo.resources.dao.UserDao;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Integer > {

	public UserEntity save(UserEntity user);

}
