package hu.szakdolgozat.pm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.pm.entity.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

	List<Sprint> findByProjectId(Long ProjectId);

	Optional<Sprint> findById(Long id);
}
