package hu.szakdolgozat.tanya.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "sprint")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "project", "tasks" })
@EqualsAndHashCode(exclude = { "project", "tasks" })
public class Sprint {

	@Id
	@GenericGenerator(name = "sprintSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SPRINT_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "sprintSequenceGenerator")
	private Long id;

	@JoinColumn(name = "project_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;

	@Column(name = "sprint_name")
	private String sprintName;

	@Column(name = "date_of_start")
	private Instant dateOfStart;

	@Column(name = "date_of_end")
	private Instant dateOfEnd;

	@OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY)
	private List<Task> tasks = new ArrayList<>();
}
