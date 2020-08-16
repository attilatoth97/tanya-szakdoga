package hu.szakdolgozat.tanya.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hu.szakdolgozat.tanya.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	@Query("select c from Calendar c where c.project.id = :pId")
	List<Calendar> getCalendars(@Param("pId") Long id);

	Optional<Calendar> findById(Long id);
}
