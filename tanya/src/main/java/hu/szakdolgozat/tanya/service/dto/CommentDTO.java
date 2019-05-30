package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {

	private Long id;

	private String text;

	private String userFullName;

	private Instant createDate;
}
