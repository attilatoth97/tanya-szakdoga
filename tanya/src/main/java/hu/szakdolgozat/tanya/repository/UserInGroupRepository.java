package hu.szakdolgozat.tanya.repository;

import java.util.List;
import java.util.Optional;

import hu.szakdolgozat.tanya.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.entity.UserInGroup;

@Repository
public interface UserInGroupRepository extends JpaRepository<UserInGroup, Long> {

	@Query("DELETE FROM UserInGroup WHERE user.id = :userId and group.id = :groupId ")
	void deleteUserFromGroup(@Param("groupId") Long groupId, @Param("userId") Long userId);

	@Query("SELECT uig.group FROM UserInGroup uig WHERE uig.user.id = :userId and uig.group.createUser.id != :userId")
	List<Group> getGroupsWhereUserAttendant(@Param("userId") Long userId);
}
