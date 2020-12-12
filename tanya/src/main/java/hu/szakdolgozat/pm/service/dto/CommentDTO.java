package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {

	private Long id;

	private String text;

	private String userFullName;

	private Instant createDate;

	private boolean own;
}
