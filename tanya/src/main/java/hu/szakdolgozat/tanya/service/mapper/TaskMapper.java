package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.TaskEditorDTO;
import hu.szakdolgozat.tanya.service.dto.TaskMiniDTO;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	Task toEntity(TaskEditorDTO dto);

	@Mapping(source = "createUser.userName", target = "createUserName")
	@Mapping(source = "responsibleUser.userName", target = "responsibleUserName")
	TaskMiniDTO toMiniDTO(Task entity);

	TaskDTO toDTO(Task entity);
}
