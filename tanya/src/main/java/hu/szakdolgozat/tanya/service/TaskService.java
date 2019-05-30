package hu.szakdolgozat.tanya.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Sprint;
import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.TaskRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.TaskEditorDTO;
import hu.szakdolgozat.tanya.service.mapper.TaskMapper;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private SprintService sprintService;

	public TaskDTO save(TaskEditorDTO editorDTO) {
		User creater = userService.getLoggedUser();
		Sprint sprint = sprintService.findOne(editorDTO.getSprintId());
		if (sprint == null) {
			throw new TanyaException("Nincs ilyen azonosítójú sprint");

		}
		if (!authorityService.isMemberForTheGroup(sprint.getProject().getGroup().getId(),
				UserUtil.getAuthenticatedUser().getId())) {
			throw new TanyaException("Nincs jogosultságod ehhez a csoporthoz");
		}

		Task task = taskMapper.toEntity(editorDTO);
		task.setCreateUser(creater);
		task.setIsClose(false);
		task.setDateOfCreate(Instant.now());
		task.setDateOfLastRevisal(task.getDateOfCreate());
		task.setSprint(sprint);
		task = taskRepository.save(task);
		return taskMapper.toDTO(task);
	}

	public TaskDTO update(TaskEditorDTO editorDTO) {
		return null;
	}

	public TaskDTO getTask(Long id) {
		Task task = findOne(id);
		if (task == null) {
			throw new TanyaException("Nincs ilyen azonosítójú feladat");
		}

		if (!authorityService.isMemberForTheGroup(task.getSprint().getProject().getGroup().getId(),
				UserUtil.getAuthenticatedUser().getId())) {
			throw new TanyaException("Nincs jogosultságod ehhez a csoporthoz");
		}

		TaskDTO result = taskMapper.toDTO(task);
		result.setCreateUserName(task.getCreateUser().getFullName());
		result.setResponsibleUserName(task.getResponsibleUser().getFullName());
		return result;
	}

	public void setClosedIssue(Long id) {
		Task task = findOne(id);
		task.setIsClose(true);
	}

	protected Task findOne(Long id) {
		return taskRepository.getOne(id);
	}

}
