package hu.szakdolgozat.tanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.UserWorking;

@Repository
public interface UserWorkingRepository extends JpaRepository<UserWorking, Long> {

}
