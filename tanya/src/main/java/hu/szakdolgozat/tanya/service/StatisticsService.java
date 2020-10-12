package hu.szakdolgozat.tanya.service;

import ch.qos.logback.core.status.InfoStatus;
import hu.szakdolgozat.tanya.entity.DevelopmentLog;
import hu.szakdolgozat.tanya.entity.Group;
import hu.szakdolgozat.tanya.entity.enumeration.IssueStatus;
import hu.szakdolgozat.tanya.repository.DevelopmentLogRepository;
import hu.szakdolgozat.tanya.repository.TaskRepository;
import hu.szakdolgozat.tanya.repository.UserInGroupRepository;
import hu.szakdolgozat.tanya.security.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class StatisticsService {

    @Autowired
    private UserInGroupRepository userInGroupRepository;

    @Autowired
    private DevelopmentLogRepository developmentLogRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Long getGroupNumber() {
        return userInGroupRepository.findByUserId(UserUtil.getAuthenticatedUser().getId()).stream().count();
    }

    public Long getProjectNumber() {
        return (long) userInGroupRepository.findByUserId(UserUtil.getAuthenticatedUser().getId()).stream().map(Group::getProjects).map(Set::size).mapToInt(Integer::intValue).sum();
    }

    public Long getDevelopmentLogSum() {
        return (long) developmentLogRepository.findByUserId(UserUtil.getAuthenticatedUser().getId()).stream()
                .map(DevelopmentLog::getDevelopedHours)
                .mapToInt(Byte::byteValue)
                .sum();
    }

    public Long getCreatedTaskNumber() {
        return taskRepository.findByCreateUserId(UserUtil.getAuthenticatedUser().getId()).stream().count();
    }

    public Long getCompletedTaskNumber() {
        return taskRepository.findByResponsibleUserId(UserUtil.getAuthenticatedUser().getId()).stream().filter(e -> IssueStatus.CLOSE == e.getIssueStatus()).count();
    }

    public Map<String, Integer> getDevelopmentLog() {
        Instant startDate = LocalDate.now().minusDays(7).atTime(0,0,0).atZone(TimeZone.getTimeZone("1").toZoneId()).toInstant();
        Instant endDate = LocalDate.now().atTime(23,59, 59).atZone(TimeZone.getTimeZone("1").toZoneId()).toInstant();
        List<DevelopmentLog> currentUserLogs =  this.developmentLogRepository.findByUserId(UserUtil.getAuthenticatedUser().getId())
                .stream().filter(e -> e.getDay().isAfter(startDate) && e.getDay().isBefore(endDate))
                .sorted(Comparator.comparing(DevelopmentLog::getDay, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
        return  currentUserLogs.stream().collect(groupingBy(developmentLog -> developmentLog.getDay().toString(), summingInt(DevelopmentLog::getDevelopedHours)));
    }

    public Map<String, Integer> getDevelopmentLogByGroup() {
        List<DevelopmentLog> currentUserLogs =  this.developmentLogRepository.findByUserId(UserUtil.getAuthenticatedUser().getId());
        return currentUserLogs.stream().collect(groupingBy(developmentLog -> developmentLog.getProject().getProjectName(), summingInt(DevelopmentLog::getDevelopedHours)));
    }

}
