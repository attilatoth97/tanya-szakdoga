package hu.szakdolgozat.pm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.pm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

	@Query("SELECT u FROM User u WHERE u.userName = :username")
	User findUserByUserName(@Param("username") String username);

	User findByUserNameAndPassword(String userName, String password);

	Optional<User> findById(Long id);
}
