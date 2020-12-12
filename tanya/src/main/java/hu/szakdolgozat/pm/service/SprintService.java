package hu.szakdolgozat.pm.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.pm.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.pm.entity.Project;
import hu.szakdolgozat.pm.entity.Sprint;
import hu.szakdolgozat.pm.repository.SprintRepository;
import hu.szakdolgozat.pm.security.UserUtil;
import hu.szakdolgozat.pm.service.dto.SprintDTO;
import hu.szakdolgozat.pm.service.dto.SprintEditerDTO;
import hu.szakdolgozat.pm.service.dto.SprintMapDTO;
import hu.szakdolgozat.pm.service.mapper.SprintMapper;

@Service
public class SprintService extends AuthorityService {

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private SprintMapper sprintMapper;

	@Autowired
	private ProjectService projectService;

	public Sprint findOne(Long id) {
		Sprint sprint = sprintRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!isMemberForTheGroup(sprint.getProject().getGroup(), UserUtil.getAuthenticatedUser().getId())) {
			throw new ResourceNotFoundException();
		}
		return sprint;
	}

	public SprintDTO save(SprintEditerDTO dto) {
		Project project = projectService.findOne(dto.getProjectId());
		Sprint sprint = sprintMapper.toEntity(dto);
		sprint.setProject(project);
		sprint = sprintRepository.save(sprint);
		return sprintMapper.toDTO(sprint);
	}

	public List<SprintDTO> getSprintByProjectId(Long projectId) {
		List<Sprint> sprints = sprintRepository.findByProjectId(projectId);
		return  sprints.stream().map(sprintMapper::toDTO).collect(Collectors.toList());
		// TODO átgondolni task > fullname
	}

	public List<SprintMapDTO> getAllSprint(Long projectId) {
		Project project = projectService.findOne(projectId);
		return project.getSprints().stream().map(sprintMapper::toMapDTO).collect(Collectors.toList());
	}
}
