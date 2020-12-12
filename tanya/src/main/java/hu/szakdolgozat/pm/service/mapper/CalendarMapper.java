package hu.szakdolgozat.pm.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.pm.entity.Calendar;
import hu.szakdolgozat.pm.service.dto.CalendarDTO;
import hu.szakdolgozat.pm.service.dto.CalendarEditorDTO;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

	@Mapping(source = "project.projectName", target = "projectName")
	CalendarDTO toDTO (Calendar entity);
	
	Calendar toEntity (CalendarEditorDTO dto);
}
