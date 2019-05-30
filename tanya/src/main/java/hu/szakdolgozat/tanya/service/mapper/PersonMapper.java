package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.tanya.entity.Person;
import hu.szakdolgozat.tanya.service.dto.PersonDTO;

@Mapper( componentModel = "spring" )
public interface PersonMapper {

	Person toEntity(PersonDTO dto);
	
	PersonDTO toDTO(Person entity);
}
