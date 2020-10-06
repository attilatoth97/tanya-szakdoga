package hu.szakdolgozat.tanya.service;

import hu.szakdolgozat.tanya.entity.DevelopmentLog;
import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.exception.ResourceNotFoundException;
import hu.szakdolgozat.tanya.repository.DevelopmentLogRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.dto.request.DevelopmentLogCreateDTO;
import hu.szakdolgozat.tanya.service.dto.response.DevelopmentLogDTO;
import hu.szakdolgozat.tanya.service.mapper.DevelopmentLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DevelopmentLogService extends AuthorityService {

    @Autowired
    private DevelopmentLogRepository developmentLogRepository;

    @Autowired
    private DevelopmentLogMapper developmentLogMapper;

    @Autowired
    private TaskService taskService;

    public DevelopmentLogDTO create(DevelopmentLogCreateDTO developmentLogCreateDTO) {
        DevelopmentLog developmentLog = developmentLogMapper.toEntity(developmentLogCreateDTO);
        developmentLog.setUser(new User(UserUtil.getAuthenticatedUser().getId()));
        Task task = taskService.findOne(developmentLogCreateDTO.getTaskId());
        developmentLog.setTask(task);
        developmentLog.setProject(task.getSprint().getProject());
        DevelopmentLog savedEntity = developmentLogRepository.save(developmentLog);
        return developmentLogMapper.toDTO(savedEntity);
    }

    public List<DevelopmentLogDTO> findAllByTaskId(Long id){
        return developmentLogRepository.findByTaskId(id).stream().map(developmentLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DevelopmentLogDTO> findAllByProjectId(Long id) {
        return developmentLogRepository.findByTaskSprintProjectId(id).stream()
                .map(developmentLogMapper::toDTO).collect(Collectors.toList());
    }

    public List<DevelopmentLogDTO> findAllByCurrentUserId() {
        return developmentLogRepository.findByUserId(UserUtil.getAuthenticatedUser().getId()).stream().map(developmentLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DevelopmentLogDTO update(Long id, DevelopmentLogCreateDTO createDTO) {
        DevelopmentLog developmentLog = this.findById(id);
        if(!developmentLog.getUser().getId().equals(UserUtil.getAuthenticatedUser().getId())) {
            throw new ResourceNotFoundException();
        }
        developmentLog.setDescription(createDTO.getDescription());
        developmentLog.setDay(createDTO.getDay());
        developmentLog.setDevelopedHours(createDTO.getDevelopedHours());


        DevelopmentLog savedEntity = developmentLogRepository.save(developmentLog);
        return developmentLogMapper.toDTO(savedEntity);
    }

    public void delete(Long id) {
        DevelopmentLog developmentLog = this.findById(id);
        if(!developmentLog.getUser().getId().equals(UserUtil.getAuthenticatedUser().getId())) {
            throw new ResourceNotFoundException();
        }
        developmentLogRepository.delete(developmentLog);
    }

    public DevelopmentLogDTO getDevelopmentLog(Long id) {
        return developmentLogMapper.toDTO(findById(id));
    }

    protected DevelopmentLog findById(Long id) {
        DevelopmentLog developmentLog = developmentLogRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if(!isMemberForTheGroup(developmentLog.getTask().getSprint().getProject().getGroup().getId(),
                UserUtil.getAuthenticatedUser().getId())){
            throw new ResourceNotFoundException();
        }
        return developmentLog;
    }
}
