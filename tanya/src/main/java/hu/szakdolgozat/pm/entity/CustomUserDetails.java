package hu.szakdolgozat.pm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.szakdolgozat.pm.entity.enumeration.UserRole;

public class CustomUserDetails extends User implements UserDetails {

	Collection<? extends GrantedAuthority> authorities;
	
	private static final long serialVersionUID = 1L;

	public CustomUserDetails(User user) {
		super(user);
		
		List<GrantedAuthority> auths = new ArrayList<>();
		for(UserRole role : user.getRole()) {
			auths.add(new SimpleGrantedAuthority(role.toString()));
		}
		
		this.authorities = auths;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	} 

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}


}
