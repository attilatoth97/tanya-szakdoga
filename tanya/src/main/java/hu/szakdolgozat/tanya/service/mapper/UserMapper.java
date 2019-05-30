package hu.szakdolgozat.tanya.service.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.entity.enumeration.UserRole;
import hu.szakdolgozat.tanya.service.dto.UserDTO;
import hu.szakdolgozat.tanya.service.dto.UserEditerDTO;
import hu.szakdolgozat.tanya.service.dto.UserMiniDTO;

@Mapper(uses = { PersonMapper.class }, componentModel = "spring")
public abstract class UserMapper {

	@Autowired
	private PersonMapper personMapper;

	public abstract UserDTO toDTO(User entity);

	public abstract UserEditerDTO toEditerDTO(User entity);

	public abstract User ById(Long id);

	public abstract UserMiniDTO toMiniDTO(User entity);

	public User toEntity(UserEditerDTO dto) {
		if (dto == null) {
			return null;
		}

		User user = new User();

		user.setId(dto.getId());
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setPerson(personMapper.toEntity(dto.getPerson()));

		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(UserRole.USER);
		user.setRole(roles);

		return user;
	}

}
