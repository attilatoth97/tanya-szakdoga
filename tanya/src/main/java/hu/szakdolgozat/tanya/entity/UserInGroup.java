package hu.szakdolgozat.tanya.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "user_in_group")
@Data
@ToString(exclude = { "user", "group" })
@EqualsAndHashCode(exclude = { "user", "group" })
public class UserInGroup {

	@Id
	@GenericGenerator(name = "userInGroupSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "USER_IN_GROUP_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "userInGroupSequenceGenerator")
	private Long id;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "group_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;
}
