package hu.szakdolgozat.tanya.repository;

import java.util.List;
import java.util.Optional;

import hu.szakdolgozat.tanya.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByTaskId(Long id);

	Optional<Comment> findById(Long id);
}
