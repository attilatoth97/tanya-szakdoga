package hu.szakdolgozat.pm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.pm.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByResponsibleUserId(Long id);

	List<Task> findByCreateUserId(Long id);

	Optional<Task> findById(Long id);
}
