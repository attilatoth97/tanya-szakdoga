package hu.szakdolgozat.tanya.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "sprints", "users", "group", "createUser", "calendars", "logs" })
@EqualsAndHashCode(exclude = { "sprints", "users", "group", "createUser", "calendars", "logs" })
public class Project {

	@Id
	@GenericGenerator(name = "projectSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "PROJECT_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "projectSequenceGenerator")
	private Long id;

	@NotBlank
	@Column(name = "project_name")
	private String projectName;

	@JoinColumn(name = "group_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;

	@Length(max = 1000)
	private String description;

	@JoinColumn(name = "create_user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User createUser;

	@NotNull
	@Column(name = "is_finished")
	private Boolean isFinished;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Sprint> sprints = new HashSet<>();

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private Set<UserWorking> users = new HashSet<>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Calendar> calendars = new HashSet<>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<DevelopmentLog> logs = new HashSet<>();

}
