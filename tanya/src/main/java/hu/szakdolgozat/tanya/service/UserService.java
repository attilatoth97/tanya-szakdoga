package hu.szakdolgozat.tanya.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.UserRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.dto.UserDTO;
import hu.szakdolgozat.tanya.service.dto.UserEditerDTO;
import hu.szakdolgozat.tanya.service.mapper.UserMapper;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserDTO save(UserEditerDTO dto) {
		User userByUserName = findUserByUserName(dto.getUserName());
		if (userByUserName != null) {
			return null;
		}
		User user = userMapper.toEntity(dto);
		user = userRepository.save(user);
		return userMapper.toDTO(user);
	}

	public UserDTO update(UserEditerDTO dto) {
		if (UserUtil.getAuthenticatedUser().getId() != dto.getId()) {
			throw new TanyaException("Nincs jogosultságod ilyen műveletre!");
		}
		User user = findOne(dto.getId());
		user.setPassword(dto.getPassword());
		user.getPerson().setLastName(dto.getPerson().getLastName());
		user.getPerson().setFirstName(dto.getPerson().getFirstName());
		user.getPerson().setEmail(dto.getPerson().getEmail());
		user.getPerson().setPlaceOfBirth(dto.getPerson().getPlaceOfBirth());
		user.getPerson().setDateOfBirth(dto.getPerson().getDateOfBirth());
		user = userRepository.save(user);
		return userMapper.toDTO(user);

	}

	public User findUserByUserName(String username) {
		return userRepository.findUserByUserName(username);
	}

	public UserDTO getUserDTO(Long id) {
		return userMapper.toDTO(userRepository.getOne(id));
	}

	public List<UserDTO> getAllUser() {
		return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
	}

	public boolean userLogged() {
		return UserUtil.getAuthenticatedUser().getUsername() == null ? false : true;
	}

	public UserEditerDTO getLoggedEditerUser() {
		User user = getLoggedUser();
		return userMapper.toEditerDTO(user);
	}

	public User getLoggedUser() {
		return userRepository.getOne(UserUtil.getAuthenticatedUser().getId());
	}

	public User getUserByUserNameAndPassword(String userName, String password) {
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if (user == null) {
			throw new TanyaException("Nem található ilyen felhasználónév és jelszó párosítás az adatbázisban!");
		}
		return user;
	}

	public User findOne(Long id) {
		return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

}
