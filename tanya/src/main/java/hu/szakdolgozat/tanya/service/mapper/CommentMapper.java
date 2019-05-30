package hu.szakdolgozat.tanya.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.szakdolgozat.tanya.entity.Comment;
import hu.szakdolgozat.tanya.service.dto.CommentDTO;
import hu.szakdolgozat.tanya.service.dto.CommentEditerDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	Comment toEntity(CommentEditerDTO dto);

	@Mapping(ignore = true, target = "userFullName")
	CommentDTO toDTO(Comment entity);
}
