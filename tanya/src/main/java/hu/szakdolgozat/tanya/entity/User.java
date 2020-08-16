package hu.szakdolgozat.tanya.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import hu.szakdolgozat.tanya.entity.enumeration.UserRole;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "person", "createdProjects", "userWorkings", "responsibleForTasks", "createdTasks", "comments",
		"calendars", "logs" })
@EqualsAndHashCode(exclude = { "person", "createdProjects", "userWorkings", "responsibleForTasks", "createdTasks",
		"comments", "calendars", "logs" })
public class User {

	@Id
	@GenericGenerator(name = "userTableSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "USER_TABLE_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "userTableSequenceGenerator")
	private Long id;

	@NotBlank
	private String userName;

	private String password;

	@ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role")
	@Column(name = "user_role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<UserRole> role = new HashSet<UserRole>();

	@JoinColumn(name = "person_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Person person;

	@OneToMany(mappedBy = "createUser", fetch = FetchType.LAZY)
	private Set<Group> createdGroups;

	@OneToMany(mappedBy = "createUser", fetch = FetchType.LAZY)
	private Set<Project> createdProjects;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserWorking> userWorkings;

	@OneToMany(mappedBy = "responsibleUser", fetch = FetchType.LAZY)
	private Set<Task> responsibleForTasks;

	@OneToMany(mappedBy = "createUser", fetch = FetchType.LAZY)
	private Set<Task> createdTasks;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "createUser", fetch = FetchType.LAZY)
	private Set<Calendar> calendars;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<DevelopmentLog> logs;

	public User(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.person = user.getPerson();
		this.createdProjects = user.getCreatedProjects();
		this.userWorkings = user.getUserWorkings();
		this.responsibleForTasks = user.getResponsibleForTasks();
		this.createdTasks = user.getCreatedTasks();
		this.comments = user.getComments();
		this.calendars = user.getCalendars();
		this.logs = user.getLogs();
		this.role = user.getRole();
	}

	public User(String userName, String password, Set<UserRole> role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getFullName() {
		return this.person.getLastName() + " " + this.person.getFirstName();
	}

}
