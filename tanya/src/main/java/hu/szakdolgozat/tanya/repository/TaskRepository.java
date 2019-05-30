package hu.szakdolgozat.tanya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByResponsibleUserId(Long id);

	List<Task> findByCreateUserId(Long id);

}
