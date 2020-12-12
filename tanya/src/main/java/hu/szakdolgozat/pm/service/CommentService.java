package hu.szakdolgozat.pm.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hu.szakdolgozat.pm.web.exception.ResourceNotFoundException;
import hu.szakdolgozat.pm.security.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szakdolgozat.pm.entity.Comment;
import hu.szakdolgozat.pm.entity.Task;
import hu.szakdolgozat.pm.entity.User;
import hu.szakdolgozat.pm.repository.CommentRepository;
import hu.szakdolgozat.pm.service.dto.CommentDTO;
import hu.szakdolgozat.pm.service.dto.CommentEditerDTO;
import hu.szakdolgozat.pm.service.mapper.CommentMapper;

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
