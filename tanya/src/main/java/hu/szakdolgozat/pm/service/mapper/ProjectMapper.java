package hu.szakdolgozat.pm.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.pm.entity.Project;
import hu.szakdolgozat.pm.service.dto.ProjectDTO;
import hu.szakdolgozat.pm.service.dto.ProjectEditerDTO;
import hu.szakdolgozat.pm.service.dto.ProjectMapDTO;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

	Project toEntity(ProjectEditerDTO dto);

	ProjectEditerDTO toEditerDTO(Project entity);

	@Mapping(source = "projectName", target = "projectName")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "createUser.userName", target = "createrUsername")
	ProjectDTO toDTO(Project entity);

	ProjectMapDTO toMapDTO(Project entity);
}
