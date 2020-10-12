package hu.szakdolgozat.tanya.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	@Query("SELECT g FROM Group g WHERE g.groupName = :groupName AND g.createUser.id = :userId")
	Group findByGroupNameAndUserId(@Param("userId") Long userId, @Param("groupName") String groupName);

	@Query("SELECT g FROM Group g WHERE g.createUser.id = :userId")
	List<Group> findAllGroupForUserCreated(@Param("userId") Long userId);

	Optional<Group> findById(Long id);
}
