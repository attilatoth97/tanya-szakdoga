package hu.szakdolgozat.tanya.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMiniDTO {

	private Long id;
	
	private String userName;
}
