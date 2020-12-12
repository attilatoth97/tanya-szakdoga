package hu.szakdolgozat.pm.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import hu.szakdolgozat.pm.service.CommentService;
import hu.szakdolgozat.pm.service.dto.CommentDTO;
import hu.szakdolgozat.pm.service.dto.CommentEditerDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentResource {

	@Autowired
	private CommentService commentService;

	@PutMapping("/comment")
	public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentEditerDTO dto) {
		return ResponseEntity.ok().body(commentService.save(dto));
	}

	@GetMapping("/comment/task/{id}")
	public ResponseEntity<List<CommentDTO>> getCommentInTask(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(commentService.findAllCommentByTaskId(id));
	}

	@DeleteMapping("/comment")
	public ResponseEntity<Void> delete(@RequestParam Long id) {
		commentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
