package hu.szakdolgozat.pm.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import hu.szakdolgozat.pm.config.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
@Slf4j
public class JwtValidator {

	private static final String secret = "demo";

	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get("userId")));
			jwtUser.setRole((String) body.get("role"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		return jwtUser;
	}

}
