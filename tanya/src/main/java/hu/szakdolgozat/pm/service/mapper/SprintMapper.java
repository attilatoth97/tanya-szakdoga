package hu.szakdolgozat.pm.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.pm.entity.Sprint;
import hu.szakdolgozat.pm.service.dto.SprintDTO;
import hu.szakdolgozat.pm.service.dto.SprintEditerDTO;
import hu.szakdolgozat.pm.service.dto.SprintMapDTO;

@Mapper(uses = { TaskMapper.class }, componentModel = "spring")
public interface SprintMapper {

	Sprint toEntity(SprintEditerDTO dto);

	SprintDTO toDTO(Sprint entity);

	SprintMapDTO toMapDTO(Sprint entity);
}
