package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;

import hu.szakdolgozat.tanya.entity.Sprint;
import hu.szakdolgozat.tanya.service.dto.SprintDTO;
import hu.szakdolgozat.tanya.service.dto.SprintEditerDTO;
import hu.szakdolgozat.tanya.service.dto.SprintMapDTO;

@Mapper(uses = { TaskMapper.class }, componentModel = "spring")
public interface SprintMapper {

	Sprint toEntity(SprintEditerDTO dto);

	SprintDTO toDTO(Sprint entity);

	SprintMapDTO toMapDTO(Sprint entity);
}
