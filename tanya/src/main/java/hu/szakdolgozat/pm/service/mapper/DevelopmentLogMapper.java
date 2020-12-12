package hu.szakdolgozat.pm.service.mapper;

import hu.szakdolgozat.pm.entity.DevelopmentLog;
import hu.szakdolgozat.pm.service.dto.request.DevelopmentLogCreateDTO;
import hu.szakdolgozat.pm.service.dto.response.DevelopmentLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DevelopmentLogMapper {


    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    DevelopmentLog toEntity(DevelopmentLogCreateDTO createDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "projectId", source = "task.sprint.project.id")
    @Mapping(target = "projectName", source = "task.sprint.project.projectName")
    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "taskName", source = "task.issueName")
    DevelopmentLogDTO toDTO(DevelopmentLog entity);
}
