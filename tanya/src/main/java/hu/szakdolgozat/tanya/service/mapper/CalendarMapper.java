package hu.szakdolgozat.tanya.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.tanya.entity.Calendar;
import hu.szakdolgozat.tanya.service.dto.CalendarDTO;
import hu.szakdolgozat.tanya.service.dto.CalendarEditorDTO;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

	@Mapping(source = "project.projectName", target = "projectName")
	CalendarDTO toDTO (Calendar entity);
	
	Calendar toEntity (CalendarEditorDTO dto);
}
