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

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"project", "createUser"} )
@EqualsAndHashCode(exclude = {"project", "createUser"} )
public class Calendar {

	@Id
	@GenericGenerator(
			name="calendarSequenceGenerator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					 @Parameter(name= "sequence_name", value = "CALENDAR_ID_SEQ"),
					 @Parameter(name= "initial_value", value = "1"),
					 @Parameter(name= "increment_size", value = "1"),
			})
	@GeneratedValue(generator="calendarSequenceGenerator")
	private Long id;
	
	@NotBlank
	@Length(max = 20)
	private String title;
	
	@Length(max = 500)
	private String description;
	
	@JoinColumn(name = "project_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@JoinColumn(name = "create_user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User createUser;
	
	@Column(name="date")
	private Instant date;
	
	/*
	@Column(name="date_of_end")
	private Instant dateOfEnd;
	*/
	
}
