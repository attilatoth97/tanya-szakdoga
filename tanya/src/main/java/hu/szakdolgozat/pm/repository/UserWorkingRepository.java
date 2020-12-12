package hu.szakdolgozat.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.pm.entity.UserWorking;

@Repository
public interface UserWorkingRepository extends JpaRepository<UserWorking, Long> {

}
