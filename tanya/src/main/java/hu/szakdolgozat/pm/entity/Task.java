package hu.szakdolgozat.pm.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import hu.szakdolgozat.pm.entity.enumeration.IssueStatus;
import hu.szakdolgozat.pm.entity.enumeration.IssueType;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = { "createUser", "sprint", "responsibleUser", "comments", "developmentLogs" })
@EqualsAndHashCode(exclude = { "createUser", "sprint", "responsibleUser", "comments", "developmentLogs" })
public class Task {

	@Id
	@GenericGenerator(name = "taskSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "TASK_ID_SEQ"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "taskSequenceGenerator")
	private Long id;

	// TODO max 20
	@NotBlank
	@Length(max = 1000)
	@Column(name = "issue_name")
	private String issueName;

	@NotNull
	@Column(name = "date_of_create")
	private Instant dateOfCreate;

	@NotNull
	@Column(name = "date_of_last_revisal")
	private Instant dateOfLastRevisal;

	@NotNull
	@JoinColumn(name = "create_user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User createUser;

	@Column(name = "description")
	@Length(max = 1000)
	private String description;

	@NotNull
	@JoinColumn(name = "sprint_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Sprint sprint;

	@JoinColumn(name = "responsible_user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User responsibleUser;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "issue_status")
	private IssueStatus issueStatus;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "issue_type")
	private IssueType issueType;

	@Column(name = "is_close")
	private Boolean isClose;

	@Column(name = "estimated_time")
	private Integer estimatedTime;

	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<DevelopmentLog> developmentLogs;
}
