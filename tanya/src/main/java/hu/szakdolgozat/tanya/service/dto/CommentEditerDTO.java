package hu.szakdolgozat.tanya.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentEditerDTO {

	private Long id;

	@NotBlank
	@Size(max = 1000)
	private String text;

	@NotNull
	private Long taskId;
}
