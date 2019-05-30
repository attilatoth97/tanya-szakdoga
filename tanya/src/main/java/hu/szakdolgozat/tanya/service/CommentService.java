package hu.szakdolgozat.tanya.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Comment;
import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.exception.TanyaException;
import hu.szakdolgozat.tanya.repository.CommentRepository;
import hu.szakdolgozat.tanya.service.dto.CommentDTO;
import hu.szakdolgozat.tanya.service.dto.CommentEditerDTO;
import hu.szakdolgozat.tanya.service.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private GroupService groupService;

	public CommentDTO save(CommentEditerDTO dto) {
		User creater = userService.getLoggedUser();

		Task task = taskService.findOne(dto.getTaskId());
		if (task == null) {
			throw new TanyaException("Nem létezik ilyen azonosítójú feladat");
		}

		if (!groupService.isMemberTheGroup(task.getSprint().getProject().getGroup().getId(), creater.getId())) {
			throw new TanyaException("Nincs hozzáférésed ehhez a csoporthoz");
		}

		Comment comment = commentMapper.toEntity(dto);
		comment.setUser(creater);
		comment.setCreateDate(Instant.now());
		comment.setTask(task);
		comment = commentRepository.save(comment);
		return commentMapper.toDTO(comment);
	}

	public List<CommentDTO> findAllCommentByTaskId(Long id) {
		List<Comment> comments = commentRepository.findByTaskId(id);
		List<CommentDTO> result = new ArrayList<>();
		comments.forEach(e -> {
			CommentDTO dto = commentMapper.toDTO(e);
			dto.setUserFullName(e.getUser().getFullName());
			result.add(dto);
		});

		return result;
	}

}
