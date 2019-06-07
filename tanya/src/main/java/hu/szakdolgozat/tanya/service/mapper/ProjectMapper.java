package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.service.dto.ProjectDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectEditerDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectMapDTO;

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
