package hu.szakdolgozat.tanya.web.resource;

import hu.szakdolgozat.tanya.service.DevelopmentLogService;
import hu.szakdolgozat.tanya.service.dto.request.DevelopmentLogCreateDTO;
import hu.szakdolgozat.tanya.service.dto.response.DevelopmentLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/development-log")
public class DevelopmentLogResource {

    @Autowired
    private DevelopmentLogService developmentLogService;

    @PostMapping
    public ResponseEntity<DevelopmentLogDTO> create(@Valid @RequestBody DevelopmentLogCreateDTO createDTO) {
        return ResponseEntity.ok(developmentLogService.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<DevelopmentLogDTO> update(@RequestParam Long id, @Valid @RequestBody DevelopmentLogCreateDTO createDTO) {
        return ResponseEntity.ok(developmentLogService.update(id, createDTO));
    }

    @GetMapping
    public ResponseEntity<DevelopmentLogDTO> getDevelopmentLog(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.getDevelopmentLog(id));
    }

    @GetMapping("/task")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByTaskId(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.findAllByTaskId(id));
    }

    @GetMapping("/project")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByProjectId(@RequestParam Long id) {
        return ResponseEntity.ok(developmentLogService.findAllByProjectId(id));
    }

    @GetMapping("/current-user")
    public ResponseEntity<List<DevelopmentLogDTO>> findAllByCurrentUserId() {
        return ResponseEntity.ok(developmentLogService.findAllByCurrentUserId());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        developmentLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
