package hu.szakdolgozat.tanya.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hu.szakdolgozat.tanya.web.exception.ResourceNotFoundException;
import hu.szakdolgozat.tanya.security.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.tanya.entity.Comment;
import hu.szakdolgozat.tanya.entity.Task;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.repository.CommentRepository;
import hu.szakdolgozat.tanya.service.dto.CommentDTO;
import hu.szakdolgozat.tanya.service.dto.CommentEditerDTO;
import hu.szakdolgozat.tanya.service.mapper.CommentMapper;

@Service
public class CommentService extends AuthorityService{

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;


	public CommentDTO save(CommentEditerDTO dto) {
		User creater = userService.getLoggedUser();
		Task task = taskService.findOne(dto.getTaskId());
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
			dto.setOwn(e.getUser().getId().equals(UserUtil.getAuthenticatedUser().getId()));
			result.add(dto);
		});

		return result;
	}

	public void delete(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if(optionalComment.isPresent()) {
			if(!optionalComment.get().getUser().getId().equals(UserUtil.getAuthenticatedUser().getId())) {
				throw new ResourceNotFoundException();
			}
			commentRepository.delete(optionalComment.get());
		}

	}

}
