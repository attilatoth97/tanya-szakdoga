package hu.szakdolgozat.tanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.DevelopmentLog;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevelopmentLogRepository extends JpaRepository<DevelopmentLog, Long> {

    Optional<DevelopmentLog> findById(Long id);

    List<DevelopmentLog> findByTaskId(Long id);

    List<DevelopmentLog> findByUserId(Long id);

    List<DevelopmentLog> findByTaskSprintProjectId(Long id);

}
