package hu.szakdolgozat.tanya.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.entity.Sprint;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.entity.UserInGroup;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.ProjectRepository;
import hu.szakdolgozat.tanya.service.dto.ProjectDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectEditerDTO;
import hu.szakdolgozat.tanya.service.dto.UserMiniDTO;
import hu.szakdolgozat.tanya.service.mapper.ProjectMapper;
import hu.szakdolgozat.tanya.service.mapper.UserMapper;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserMapper userMapper;

	public ProjectDTO save(ProjectEditerDTO editerDto) {
		User loggedUser = userService.getLoggedUser();
		Group group = groupService.findOne(editerDto.getGroupId());
		if (group == null) {
			throw new TanyaException("Nem létezik ilyen csoport!");
		}

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

	public ProjectDTO update(ProjectEditerDTO editerDto) {
		return null;

	}

	protected Project findOne(Long id) {
		return projectRepository.getOne(id);
	}

	public ProjectEditerDTO getProjectEditer(Long id) {
		Project project = findOne(id);
		if (project == null) {
			throw new TanyaException("Nem létezik ilyen projekt!");
		}

		return projectMapper.toEditerDTO(project);
	}

	public ProjectDTO getProject(Long id) {
		Project project = findOne(id);
		if (project == null) {
			throw new TanyaException("Nem létezik ilyen projekt!");
		}
		return projectMapper.toDTO(project);
	}

	public Set<ProjectDTO> getProjectsInGroup(Long groupId) {
		Group group = groupService.findOne(groupId);
		if (group == null) {
			throw new TanyaException("Nem létezik ilyen csoport!");
		}
		Set<ProjectDTO> result = new HashSet<ProjectDTO>();

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

}
