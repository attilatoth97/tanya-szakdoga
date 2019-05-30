package hu.szakdolgozat.tanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
