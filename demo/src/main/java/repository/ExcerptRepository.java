package repository;

import domain.entity.Excerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcerptRepository extends JpaRepository<Excerpt, Integer> {
}
