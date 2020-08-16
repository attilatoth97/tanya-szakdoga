package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.TaskEditorDTO;
import hu.szakdolgozat.tanya.service.dto.TaskMiniDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	Task toEntity(TaskEditorDTO dto);

	@Mapping(target = "createUserName", source = "entity.createUser.userName")
	@Mapping( target = "responsibleUserName", source = "entity.responsibleUser.userName")
	TaskMiniDTO toMiniDTO(Task entity);

	TaskDTO toDTO(Task entity);
}
