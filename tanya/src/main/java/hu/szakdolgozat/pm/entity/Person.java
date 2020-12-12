package hu.szakdolgozat.pm.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "user" })
@EqualsAndHashCode(exclude = { "user" })
public class Person {

	@Id
	@GenericGenerator(name = "personSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "PERSON_ID_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(generator = "personSequenceGenerator")
	private Long id;

	@NotBlank
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private Instant dateOfBirth;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank
	@Email
	private String email;

	@OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
	private User user;
}
