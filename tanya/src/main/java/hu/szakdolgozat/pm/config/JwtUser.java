package hu.szakdolgozat.pm.config;

public class JwtUser {

	private String userName;
	private Long id;
	private String role;

	public void setUserName(String userName) {
		this.userName = userName;

	}

	public void setId(long id) {
		this.id = id;

	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public String getRole() {
		return role;
	}

}
