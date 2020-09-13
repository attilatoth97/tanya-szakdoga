package hu.szakdolgozat.tanya.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import hu.szakdolgozat.tanya.security.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Calendar;
import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.repository.CalendarRepository;
import hu.szakdolgozat.tanya.service.dto.CalendarDTO;
import hu.szakdolgozat.tanya.service.dto.CalendarEditorDTO;
import hu.szakdolgozat.tanya.service.mapper.CalendarMapper;

@Service
public class CalendarService extends AuthorityService {

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CalendarMapper calendarMapper;
	
	@Autowired
	private ProjectService projectService;
	
	public List<CalendarDTO> findAllProjectCalendars(Long id){
		Project project = projectService.findOne(id);
		return calendarRepository.getCalendars(id).stream().map(calendarMapper::toDTO).collect(Collectors.toList());
	}
	
	public CalendarDTO getCalendarDate(Long id) {
		Calendar calendar = findOne(id);
		return calendarMapper.toDTO(calendar);
	}
	
	public CalendarDTO save(CalendarEditorDTO dto) {
		// TODO ellenörzés
		Project project = projectService.findOne(dto.getProjectId());
		User loggedUser = userService.getLoggedUser();
		Calendar calendar = calendarMapper.toEntity(dto);
		calendar.setCreateUser(loggedUser);
		calendar.setProject(project);
		calendar = calendarRepository.save(calendar);
		return calendarMapper.toDTO(calendar);
	}
	
	public CalendarDTO update(Long id, CalendarEditorDTO dto) {
		Calendar calendar = findOne(id);
		Project project = projectService.findOne(dto.getProjectId());
		calendar.setDate(dto.getDate());
		calendar.setDescription(dto.getDescription());
		calendar.setTitle(dto.getTitle());
		calendar.setProject(project);
		calendar = calendarRepository.save(calendar);
		return calendarMapper.toDTO(calendar);
	}
	
	private Calendar findOne(Long id) {
		Calendar calendar = calendarRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!isMemberForTheGroup(calendar.getProject().getGroup().getId(), UserUtil.getAuthenticatedUser().getId())){
			throw new ResourceNotFoundException();
		}
		return calendar;
	}
}
