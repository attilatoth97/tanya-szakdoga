package hu.szakdolgozat.pm.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "group_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "projects", "createUser", "users" })
@EqualsAndHashCode(exclude = { "projects", "createUser", "users" })
public class Group {

	@Id
	@GenericGenerator(name = "groupTableSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "GROUP_TABLE_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "groupTableSequenceGenerator")
	private Long id;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "create_date")
	private Instant createDate;

	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private Set<Project> projects = new HashSet<>();

	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserInGroup> users = new HashSet<>();

	@JoinColumn(name = "create_user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User createUser;

}
