package hu.szakdolgozat.tanya.service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.config.JwtUserDetails;
import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.entity.UserInGroup;
import hu.szakdolgozat.tanya.web.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.GroupRepository;
import hu.szakdolgozat.tanya.repository.UserInGroupRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.dto.GroupDTO;
import hu.szakdolgozat.tanya.service.mapper.GroupMapper;
import hu.szakdolgozat.tanya.service.mapper.UserMapper;

@Service
public class GroupService {

	private final GroupRepository groupRepository;

	private final GroupMapper groupMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private UserInGroupRepository userInGroupRepository;

	public GroupService(GroupRepository businessRepository, GroupMapper businessMapper) {
		this.groupRepository = businessRepository;
		this.groupMapper = businessMapper;
	}

	public GroupDTO getGroup(Long id) {
		return groupMapper.toDTO(findOne(id));
	}

	protected Group findOne(Long id) {
		return groupRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	public GroupDTO save(GroupDTO dto) {
		JwtUserDetails userDetails = UserUtil.getAuthenticatedUser();
		Group group = groupRepository.findByGroupNameAndUserId(userDetails.getId(), dto.getGroupName());
		if (group != null) {
			throw new TanyaException("A csoport megnevezésének felhasználóként egyedinek kell lennie");
		}

		group = groupMapper.toEntity(dto);
		group.setCreateDate(Instant.now());
		group.setCreateUser(userService.getLoggedUser());
		Group savedEntity = groupRepository.save(group);
		addNewMemberForGroup(savedEntity.getId(), userDetails.getId());
		return groupMapper.toDTO(savedEntity);

	}

	public GroupDTO update(GroupDTO dto) {
		JwtUserDetails userDetails = UserUtil.getAuthenticatedUser();
		Group group = findOne(dto.getId());
		if (userDetails.getId().equals(group.getCreateUser().getId())) {
			throw new TanyaException("Nem te vagy a csoport létrehozója");
		}

		group.setGroupName(dto.getGroupName());
		group = groupRepository.save(group);
		return groupMapper.toDTO(group);

	}

	public List<GroupDTO> getUserCreatedGroups() {
		return getUserCreatedGroupsRepositry().stream().map(groupMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	protected List<Group> getUserCreatedGroupsRepositry(){
		Long id = UserUtil.getAuthenticatedUser().getId();
		return groupRepository.findAllGroupForUserCreated(id);
	}

	public List<GroupDTO> getGroupsWhereUserAttendant() {
		return getGroupsWhereUserAttendantRepositry().stream().map(groupMapper::toDTO).collect(Collectors.toList());
	}
	
	protected List<Group> getGroupsWhereUserAttendantRepositry(){
		Long id = UserUtil.getAuthenticatedUser().getId();
		return userInGroupRepository.getGroupsWhereUserAttendant(id);
	}

	// TODO ellenőrzés logged useré e
	public void delete(Long id) {
		Group group = findOne(id);
		if(!isMemberTheGroup(group, UserUtil.getAuthenticatedUser().getId())) {
			throw new ResourceNotFoundException();
		}

		groupRepository.deleteById(id);
	}

	public void addNewMemberForGroup(Long groupId, Long userId) {
		Group group = findOne(groupId);
		User user = userService.findOne(userId);
		if(user == null) {
			throw new TanyaException("Nem létezik ilyen felhasználó!");
		} 
		addNewMemberForGroup(group, user);
	}

	public void addNewMemberForGroup(Group group, User user) {
		if (isMemberTheGroup(group, user.getId())) {
			throw new TanyaException("Ez a user már tagja a csoportnak");
		}

		UserInGroup member = new UserInGroup();
		member.setGroup(group);
		member.setUser(user);
		userInGroupRepository.save(member);
	}

	public boolean isMemberTheGroup(Group group, Long userId) {
		Set<Long> membersIds = group.getUsers().stream().map(UserInGroup::getUser).map(User::getId)
				.collect(Collectors.toSet());
		return membersIds.contains(userId);
	}

	public boolean isMemberTheGroup(Long groupId, Long userId) {
		Group group = findOne(groupId);
		return isMemberTheGroup(group, userId);
	}

	public void kickUserFromGroup(Long groupId, Long userId) {
		Group group = findOne(groupId);
		if (!isMemberTheGroup(group, userId)) {
			throw new TanyaException("Nincs hozzáférésed ehhez a csoporthoz");

		}
		Set<UserInGroup> filteredUsers = group.getUsers().stream()
				.filter(e-> e.getGroup().getId().equals(groupId) && e.getUser().getId().equals(userId))
				.collect(Collectors.toSet());
		group.setUsers(filteredUsers);
		groupRepository.save(group);
	}

	public Map<Long,String> getUserNameinGroup(Long id) {
		Map<Long,String> result = new HashMap<>();
		Group group = findOne(id);
		group.getUsers().forEach(e -> {
			result.put(e.getUser().getId(), e.getUser().getFullName());
		});
		return result;
	}

}
