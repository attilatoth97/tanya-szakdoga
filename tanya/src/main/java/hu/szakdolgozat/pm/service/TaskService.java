package hu.szakdolgozat.pm.service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.pm.entity.Project;
import hu.szakdolgozat.pm.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.pm.entity.Sprint;
import hu.szakdolgozat.pm.entity.Task;
import hu.szakdolgozat.pm.entity.User;
import hu.szakdolgozat.pm.web.exception.ProjectManagerException;
import hu.szakdolgozat.pm.repository.TaskRepository;
import hu.szakdolgozat.pm.security.UserUtil;
import hu.szakdolgozat.pm.service.dto.TaskDTO;
import hu.szakdolgozat.pm.service.dto.request.TaskEditorDTO;
import hu.szakdolgozat.pm.service.dto.TaskMiniDTO;
import hu.szakdolgozat.pm.service.mapper.TaskMapper;

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

	@Autowired
	private ProjectService projectService;

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
					throw new ProjectManagerException("Nincs jogosultságod ehhez a csoporthoz");
				}
			}
			Task taskFromDTO = taskMapper.toEntity(editorDTO);
			task.setIssueName(taskFromDTO.getIssueName());
			task.setDescription(taskFromDTO.getDescription());
			task.setIssueStatus(taskFromDTO.getIssueStatus());
			task.setIssueType(taskFromDTO.getIssueType());
			task.setSprint(sprint);
			task.setEstimatedTime(taskFromDTO.getEstimatedTime());
			task.setDateOfLastRevisal(Instant.now());
		return taskMapper.toDTO(taskRepository.save(task));
	}

	public TaskDTO getTask(Long id) {
		Task task = findOne(id);
		return taskMapper.toDTO(task);
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

	public List<TaskMiniDTO> getTasksByProjectId(Long projectId) {
		Project project = projectService.findOne(projectId);
		List<Task> tasks = project.getSprints().stream().map(Sprint::getTasks).flatMap(Collection::stream).collect(Collectors.toList());
		return tasks.stream().map(taskMapper::toMiniDTO).collect(Collectors.toList());
	}

	public void delete(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if (!isMemberForTheGroup(task.getSprint().getProject().getGroup().getId(),
				UserUtil.getAuthenticatedUser().getId())) {
			throw new ResourceNotFoundException();
		}
		taskRepository.delete(task);
	}
}
