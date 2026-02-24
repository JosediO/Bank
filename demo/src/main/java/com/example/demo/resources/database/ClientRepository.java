package com.example.demo.resources.database;



import com.example.demo.resources.dao.ClientDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDao, Long> {


}
