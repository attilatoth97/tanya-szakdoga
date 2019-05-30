package hu.szakdolgozat.tanya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

	List<Sprint> findByProjectId(Long ProjectId);
}
