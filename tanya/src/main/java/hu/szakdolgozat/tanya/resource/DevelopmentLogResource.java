package hu.szakdolgozat.tanya.resource;

import hu.szakdolgozat.tanya.service.DevelopmentLogService;
import hu.szakdolgozat.tanya.service.dto.request.DevelopmentLogCreateDTO;
import hu.szakdolgozat.tanya.service.dto.response.DevelopmentLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/development-log")
public class DevelopmentLogResource {

    @Autowired
    private DevelopmentLogService developmentLogService;

    @PostMapping
    public ResponseEntity<DevelopmentLogDTO> create(@RequestBody DevelopmentLogCreateDTO createDTO) {
        return ResponseEntity.ok(developmentLogService.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<DevelopmentLogDTO> update(@RequestParam Long id, @RequestBody DevelopmentLogCreateDTO createDTO) {
        return ResponseEntity.ok(developmentLogService.update(id, createDTO));
    }

    @GetMapping("/task")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByTaskId(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.findAllByTaskId(id));
    }

    @GetMapping("/project")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByProjectId(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.findAllByProjectId(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByUserId(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.findAllByUserId(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        developmentLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
