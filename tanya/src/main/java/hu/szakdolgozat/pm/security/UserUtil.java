package hu.szakdolgozat.pm.security;

import org.springframework.security.core.context.SecurityContextHolder;

import hu.szakdolgozat.pm.config.JwtUserDetails;

public class UserUtil {

	public static JwtUserDetails getAuthenticatedUser() {
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((authentication instanceof JwtUserDetails)) {
			return (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return null;
	}

	private UserUtil() {}

}
