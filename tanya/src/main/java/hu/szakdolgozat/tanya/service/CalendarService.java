package hu.szakdolgozat.tanya.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Calendar;
import hu.szakdolgozat.tanya.entity.Project;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.CalendarRepository;
import hu.szakdolgozat.tanya.service.dto.CalendarDTO;
import hu.szakdolgozat.tanya.service.dto.CalendarEditorDTO;
import hu.szakdolgozat.tanya.service.mapper.CalendarMapper;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CalendarMapper calendarMapper;
	
	@Autowired
	private ProjectService projectService;
	
	public List<CalendarDTO> findAllProjectCalendars(Long id){
		// User loggedUser = userService.getLoggedUser();
		// TODO project tagja e
		return calendarRepository.getCalendars(id).stream().map(calendarMapper::toDTO).collect(Collectors.toList());
	}
	
	public CalendarDTO getCalendarDate(Long id) {
		Calendar calendar = findOne(id);
		if(calendar == null) {
			throw new TanyaException("Nem található ilyen időpont!");
		}
		return calendarMapper.toDTO(calendar);
	}
	
	public CalendarDTO save(CalendarEditorDTO dto) {
		dto.setId(null);
		// TODO ellenörzés
		Project project = projectService.findOne(dto.getProjectId());
		User loggedUser = userService.getLoggedUser();
		Calendar calendar = calendarMapper.toEntity(dto);
		calendar.setCreateUser(loggedUser);
		calendar.setProject(project);
		calendar = calendarRepository.save(calendar);
		return calendarMapper.toDTO(calendar);
	}
	
	public CalendarDTO update(CalendarEditorDTO dto) {
		if(dto.getId() == null) {
			throw new TanyaException("Nem található ilyen időpont!");
		} 
		// TODO ellenörzés
		Project project = projectService.findOne(dto.getProjectId());
		Calendar calendar = findOne(dto.getId());
		calendar.setDate(dto.getDate());
		calendar.setDescription(dto.getDescription());
		calendar.setTitle(dto.getTitle());
		calendar.setProject(project);
		calendar = calendarRepository.save(calendar);
		return calendarMapper.toDTO(calendar);
	}
	
	private Calendar findOne(Long id) {
		return calendarRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
}
