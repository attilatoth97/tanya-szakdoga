package hu.szakdolgozat.pm.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.pm.entity.Person;
import hu.szakdolgozat.pm.service.dto.PersonDTO;

@Mapper( componentModel = "spring" )
public interface PersonMapper {

	Person toEntity(PersonDTO dto);
	
	PersonDTO toDTO(Person entity);
}
