package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.service.dto.GroupDTO;

@Mapper(componentModel = "spring")
public interface GroupMapper {

	Group toEntity(GroupDTO dto);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "groupName", target = "groupName")
	@Mapping(source = "createUser.userName", target = "createdUserName")
	GroupDTO toDTO(Group entity);
}
