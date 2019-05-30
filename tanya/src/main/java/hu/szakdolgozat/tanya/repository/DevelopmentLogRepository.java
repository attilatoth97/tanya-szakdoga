package hu.szakdolgozat.tanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.DevelopmentLog;

@Repository
public interface DevelopmentLogRepository extends JpaRepository<DevelopmentLog, Long> {

}
