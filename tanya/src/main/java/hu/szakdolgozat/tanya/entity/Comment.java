package hu.szakdolgozat.tanya.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="comment")
@Data
@ToString(exclude = {"task", "user"} )
@EqualsAndHashCode(exclude = {"task", "user"} )
public class Comment {

	@Id
	@GenericGenerator(
			name="commentSequenceGenerator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					 @Parameter(name= "sequence_name", value = "COMMENT_ID_SEQ"),
					 @Parameter(name= "initial_value", value = "1"),
					 @Parameter(name= "increment_size", value = "1"),
			})
	@GeneratedValue(generator="commentSequenceGenerator")
	private Long id;
	
	@NotBlank
	@Length(max = 1000)
	private String text;
	
	@NotNull
	@Column(name= "date_of_create")
	private Instant createDate;
	
	@NotNull
	@JoinColumn(name = "task_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Task task;
	
	@NotNull
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
