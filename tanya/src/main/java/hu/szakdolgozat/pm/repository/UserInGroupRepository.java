package hu.szakdolgozat.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.pm.entity.Group;
import hu.szakdolgozat.pm.entity.UserInGroup;

@Repository
public interface UserInGroupRepository extends JpaRepository<UserInGroup, Long> {

	UserInGroup findByUserIdAndGroupId(Long userId, Long groupId);

	@Query("SELECT uig.group FROM UserInGroup uig WHERE uig.user.id = :userId and uig.group.createUser.id != :userId")
	List<Group> getGroupsWhereUserAttendant(@Param("userId") Long userId);

	@Query("SELECT uig.group FROM UserInGroup uig WHERE uig.user.id = :userId")
	List<Group> findByUserId(@Param("userId") Long userId);

}
