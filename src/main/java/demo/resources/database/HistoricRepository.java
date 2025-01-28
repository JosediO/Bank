package demo.resources.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.domain.entity.HistoricEntity;

@Repository
public interface HistoricRepository extends CrudRepository<HistoricEntity, Integer>{
	public HistoricEntity save(HistoricEntity historic);
}
