package hu.szakdolgozat.tanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szakdolgozat.tanya.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
