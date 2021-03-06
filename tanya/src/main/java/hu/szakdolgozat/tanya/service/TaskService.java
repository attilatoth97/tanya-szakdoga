package hu.szakdolgozat.tanya.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
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
import hu.szakdolgozat.tanya.service.dto.TaskMiniDTO;
import hu.szakdolgozat.tanya.service.mapper.TaskMapper;

@Service
public class TaskService extends AuthorityService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private SprintService sprintService;

	public TaskDTO save(TaskEditorDTO editorDTO) {
		User creater = userService.getLoggedUser();
		Sprint sprint = sprintService.findOne(editorDTO.getSprintId());
		
		Task task = taskMapper.toEntity(editorDTO);
		if(editorDTO.getResponsibleUserId() != null) {
			User responsibleUser = userService.findOne(editorDTO.getResponsibleUserId());
			if(isMemberForTheGroup(sprint.getProject().getGroup().getId(), responsibleUser.getId())) {
				task.setResponsibleUser(responsibleUser);
			} else {
				throw new ResourceNotFoundException();
			}
		}
		
		task.setCreateUser(creater);
		task.setIsClose(false);
		task.setDateOfCreate(Instant.now());
		task.setDateOfLastRevisal(task.getDateOfCreate());
		task.setSprint(sprint);
		task = taskRepository.save(task);
		return taskMapper.toDTO(task);
	}

	public TaskDTO update(Long id, TaskEditorDTO editorDTO) {
			Task task = findOne(id);
			Sprint sprint = sprintService.findOne(editorDTO.getSprintId());
			if(editorDTO.getResponsibleUserId() != null) {
				User responsibleUser = userService.findOne(editorDTO.getResponsibleUserId());
				if(isMemberForTheGroup(sprint.getProject().getGroup().getId(), responsibleUser.getId())) {
					task.setResponsibleUser(responsibleUser);
				} else {
					throw new TanyaException("Nincs jogosultságod ehhez a csoporthoz");
				}
			}
		return  null;
	}

	public TaskDTO getTask(Long id) {
		Task task = findOne(id);
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
		Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if (!isMemberForTheGroup(task.getSprint().getProject().getGroup().getId(),
				UserUtil.getAuthenticatedUser().getId())) {
			throw new ResourceNotFoundException();
		}
		return task;
	}

	public List<TaskMiniDTO> getAllOwnCreatedTask() {
		Long userId = UserUtil.getAuthenticatedUser().getId();
		return taskRepository.findByCreateUserId(userId).stream().map(taskMapper::toMiniDTO)
				.collect(Collectors.toList());
	}

	public List<TaskMiniDTO> getAllOwnResponsibledTask() {
		Long userId = UserUtil.getAuthenticatedUser().getId();
		return taskRepository.findByResponsibleUserId(userId).stream().map(taskMapper::toMiniDTO)
				.collect(Collectors.toList());
	}

}
