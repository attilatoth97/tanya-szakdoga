package hu.szakdolgozat.tanya.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.entity.Sprint;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.SprintRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.dto.SprintDTO;
import hu.szakdolgozat.tanya.service.dto.SprintEditerDTO;
import hu.szakdolgozat.tanya.service.dto.SprintMapDTO;
import hu.szakdolgozat.tanya.service.mapper.SprintMapper;

import javax.persistence.EntityNotFoundException;

@Service
public class SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private SprintMapper sprintMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private GroupService groupService;

	public Sprint findOne(Long id) {
		return sprintRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	public SprintDTO save(SprintEditerDTO dto) {
		Project project = projectService.findOne(dto.getProjectId());
		if (project == null) {
			throw new TanyaException("Nem létezik ilyen projekt");
		}

		if (!groupService.isMemberTheGroup(project.getGroup(), UserUtil.getAuthenticatedUser().getId())) {
			throw new TanyaException("Nincs jogosultságod ilyen műveletre");
		}

		Sprint sprint = sprintMapper.toEntity(dto);
		sprint.setProject(project);
		sprint = sprintRepository.save(sprint);
		return sprintMapper.toDTO(sprint);
	}

	public List<SprintDTO> getSprintByProjectId(Long projectId) {
		List<Sprint> sprints = sprintRepository.findByProjectId(projectId);
		List<SprintDTO> result = sprints.stream().map(sprintMapper::toDTO).collect(Collectors.toList());
		// TODO átgondolni task > fullname
		return result;
	}

	public List<SprintMapDTO> getAllSprint(Long projectId) {
		Project project = projectService.findOne(projectId);
		return project.getSprints().stream().map(sprintMapper::toMapDTO).collect(Collectors.toList());
	}
}
