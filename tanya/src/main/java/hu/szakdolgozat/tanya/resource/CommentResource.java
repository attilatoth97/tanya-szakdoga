package hu.szakdolgozat.tanya.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.service.CommentService;
import hu.szakdolgozat.tanya.service.dto.CommentDTO;
import hu.szakdolgozat.tanya.service.dto.CommentEditerDTO;

@RestController
@RequestMapping("/api")
public class CommentResource {

	@Autowired
	private CommentService commentService;

	@PutMapping("/comment")
	public ResponseEntity<CommentDTO> create(@Validated @RequestBody CommentEditerDTO dto) {
		return ResponseEntity.ok().body(commentService.save(dto));
	}

	@PostMapping("/comment")
	public ResponseEntity<CommentDTO> update(@Validated @RequestBody CommentEditerDTO dto) {
		return ResponseEntity.ok().body(commentService.save(dto));
	}

	@GetMapping("/comment/task/{id}")
	public ResponseEntity<List<CommentDTO>> getCommentInTask(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(commentService.findAllCommentByTaskId(id));
	}
}
