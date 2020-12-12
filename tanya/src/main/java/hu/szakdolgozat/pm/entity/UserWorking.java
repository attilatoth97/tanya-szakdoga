package hu.szakdolgozat.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import hu.szakdolgozat.pm.entity.enumeration.ProjectRole;

@Entity
@Table(name="user_working")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "project"} )
@EqualsAndHashCode(exclude = {"user", "project"} )
public class UserWorking {

	@Id
	@GenericGenerator(
			name="userWorkingSequenceGenerator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					 @Parameter(name= "sequence_name", value = "USER_WORKING_ID_SEQ"),
					 @Parameter(name= "initial_value", value = "1"),
					 @Parameter(name= "increment_size", value = "1"),
			})
	@GeneratedValue(generator="userWorkingSequenceGenerator")
	private Long id;
	
	@JoinColumn(name="project_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(name="project_role")
	private ProjectRole projectRole;
}
