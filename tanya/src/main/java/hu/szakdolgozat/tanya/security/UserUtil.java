package hu.szakdolgozat.tanya.security;

import org.springframework.security.core.context.SecurityContextHolder;

import hu.szakdolgozat.tanya.config.JwtUserDetails;

public class UserUtil {

	public static JwtUserDetails getAuthenticatedUser() {
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((authentication instanceof JwtUserDetails)) {
			return (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return null;
	}

}
