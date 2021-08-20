package com.alcalazans.tribo.config.security.token;

import com.alcalazans.tribo.config.security.userdetails.MyUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

	@Autowired
	Environment env;

	@Value("${tribo.jwt.expiration}")
	private String expiration;
	
	@Value("${tribo.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		MyUserDetails usuario = (MyUserDetails) authentication.getPrincipal();
		var hoje = new Date();
		var dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer(env.getProperty("message.security.api.title"))
				.setSubject(usuario.getUsername())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUserName(String token) {
		var claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

}
