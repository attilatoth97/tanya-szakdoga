package hu.szakdolgozat.pm.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.pm.entity.Comment;
import hu.szakdolgozat.pm.service.dto.CommentDTO;
import hu.szakdolgozat.pm.service.dto.CommentEditerDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	Comment toEntity(CommentEditerDTO dto);

	@Mapping(ignore = true, target = "userFullName")
	CommentDTO toDTO(Comment entity);
}
