package hu.szakdolgozat.tanya.web.resource;

import hu.szakdolgozat.tanya.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsResource {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/group-number")
    private ResponseEntity<Long> getGroupNumber() {
        return ResponseEntity.ok(statisticsService.getGroupNumber());
    }

    @GetMapping("/project-number")
    private ResponseEntity<Long> getProjectNumber() {
        return ResponseEntity.ok(statisticsService.getProjectNumber());
    }

    @GetMapping("/development-log-sum")
    private ResponseEntity<Long> getDevelopmentLogSum() {
        return ResponseEntity.ok(statisticsService.getDevelopmentLogSum());
    }

    @GetMapping("/created-task-number")
    private ResponseEntity<Long> getCreatedTaskNumber() {
        return ResponseEntity.ok(statisticsService.getCreatedTaskNumber());
    }

    @GetMapping("/completed-task-number")
    private ResponseEntity<Long> getCompletedTaskNumber() {
        return ResponseEntity.ok(statisticsService.getCompletedTaskNumber());
    }

    @GetMapping("/development-log")
    private ResponseEntity<Map<String, Integer>> getDevelopmentLog() {
        return ResponseEntity.ok(statisticsService.getDevelopmentLog());
    }

    @GetMapping("/development-log-by-group")
    private ResponseEntity<Map<String, Integer>> getDevelopmentLogByGroup() {
        return ResponseEntity.ok(statisticsService.getDevelopmentLogByGroup());
    }

}
