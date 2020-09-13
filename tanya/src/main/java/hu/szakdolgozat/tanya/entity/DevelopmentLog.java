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
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="development_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"task","user"} )
@EqualsAndHashCode(exclude = {"task","user"} )
public class DevelopmentLog {

	@Id
	@GenericGenerator(
			name="developmentLogSequenceGenerator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					 @Parameter(name= "sequence_name", value = "DEVELOPMENT_LOG_ID_SEQ"),
					 @Parameter(name= "initial_value", value = "1"),
					 @Parameter(name= "increment_size", value = "1"),
			})
	@GeneratedValue(generator="developmentLogSequenceGenerator")
	private Long id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@NotNull
	@Column(name="developed_hours")
	private Byte developedHours; 
	
	@NotNull
	private Instant day;
	
	@JoinColumn(name = "task_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Task task;
	
	@Length(max = 1000)
	private String description;
}
