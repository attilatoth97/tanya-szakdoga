package hu.szakdolgozat.tanya.service;

import java.util.*;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import hu.szakdolgozat.tanya.security.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.entity.Sprint;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.entity.UserInGroup;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.ProjectRepository;
import hu.szakdolgozat.tanya.service.dto.ProjectDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectEditerDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectMapDTO;
import hu.szakdolgozat.tanya.service.dto.UserMiniDTO;
import hu.szakdolgozat.tanya.service.mapper.ProjectMapper;
import hu.szakdolgozat.tanya.service.mapper.UserMapper;

//TODO [me] Outsourcing WebService
@Service
@Transactional
public class ProjectService extends AuthorityService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	public ProjectDTO save(ProjectEditerDTO editerDto) {
		User loggedUser = userService.getLoggedUser();
		Group group = groupService.findOne(editerDto.getGroupId());

		if (!isProjectNameUnique(editerDto.getGroupId(), editerDto.getProjectName())) {
			throw new TanyaException("A projektnevének egyedinek kell lennie!");
		}
		Project project = projectMapper.toEntity(editerDto);
		project.setCreateUser(loggedUser);
		project.setIsFinished(false);
		project.setGroup(group);
		project = projectRepository.save(project);
		return projectMapper.toDTO(project);

	}

	//TODO [me] Implements
	public ProjectDTO update(ProjectEditerDTO editerDto) {
		return null;
	}

	protected Project findOne(Long id) {
		Project project = projectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!isMemberForTheGroup(project.getGroup().getId(), UserUtil.getAuthenticatedUser().getId())){
			throw new ResourceNotFoundException();
		}
		return project;
	}

	public ProjectEditerDTO getProjectEditer(Long id) {
		Project project = findOne(id);
		return projectMapper.toEditerDTO(project);
	}

	public ProjectDTO getProject(Long id) {
		Project project = findOne(id);
		return projectMapper.toDTO(project);
	}

	public Set<ProjectDTO> getProjectsInGroup(Long groupId) {
		Group group = groupService.findOne(groupId);
		if (group == null) {
			throw new TanyaException("Nem létezik ilyen csoport!");
		}
		Set<ProjectDTO> result = new HashSet<>();

		group.getProjects().stream().forEach(e -> {
			ProjectDTO dto = projectMapper.toDTO(e);
			dto.setSprintNumber(e.getSprints().stream().count());
			dto.setTaskNumber(e.getSprints().stream().map(Sprint::getTasks).flatMap(List::stream).count());
			result.add(dto);
		});

		return result;
	}

	public Set<String> getProjectNamesInGroup(Long groupId) {
		return getProjectsInGroup(groupId).stream().map(ProjectDTO::getProjectName).collect(Collectors.toSet());
	}

	public boolean isProjectNameUnique(Long groupId, String projectName) {
		List<Project> projects = projectRepository.findByGroupId(groupId);
		if(projects.stream().map(Project::getProjectName).collect(Collectors.toList()).contains(projectName)) {
			return false;
		}
		return true;
	}

	public List<UserMiniDTO> getAllMiniUserInGroup(Long id) {
		Project project = findOne(id);
		Group group = project.getGroup();
		return group.getUsers().stream().map(UserInGroup::getUser).map(userMapper::toMiniDTO)
				.collect(Collectors.toList());
	}
	
	public List<ProjectMapDTO> getProjectMiniDTOOwn(){
		List<ProjectMapDTO> result = new ArrayList<>();
		result.addAll(groupService.getGroupsWhereUserAttendantRepositry().stream().map(Group::getProjects).flatMap(Set::stream).map(projectMapper::toMapDTO).collect(Collectors.toList()));
		result.addAll(groupService.getUserCreatedGroupsRepositry().stream().map(Group::getProjects).flatMap(Set::stream).map(projectMapper::toMapDTO).collect(Collectors.toList()));
		return result;
	}

}
