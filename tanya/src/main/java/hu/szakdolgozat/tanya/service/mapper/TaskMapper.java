package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.request.TaskEditorDTO;
import hu.szakdolgozat.tanya.service.dto.TaskMiniDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	Task toEntity(TaskEditorDTO dto);

	@Mapping(target = "createUserName", source = "entity.createUser.userName")
	@Mapping( target = "responsibleUserName", source = "entity.responsibleUser.userName")
	TaskMiniDTO toMiniDTO(Task entity);

	@Mapping(target = "sprintName", source = "entity.sprint.sprintName")
	@Mapping(target = "sprintId", source = "entity.sprint.id")
	@Mapping(target = "projectName", source = "entity.sprint.project.projectName")
	@Mapping(target = "projectId", source = "entity.sprint.project.id")
	@Mapping(target = "createUserName", source = "entity.createUser.userName")
	@Mapping( target = "responsibleUserName", source = "entity.responsibleUser.userName")
	@Mapping( target = "responsibleUserId", source = "entity.responsibleUser.id")
	TaskDTO toDTO(Task entity);
}
