package br.com.joaogabriel.imagesrepo.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.exception.InvalidTokenException;
import br.com.joaogabriel.imagesrepo.jwt.response.TokenResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	private final SecretKeyGenerator keyGenerator;
	
	public JwtService(SecretKeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public TokenResponse generate(User user) {
		
		SecretKey key = keyGenerator.getKey();
		Date expiration = generateExpirationDate();
		Map<String, Object> claims = generateTokenClaims(user);
		
		String token = Jwts.builder()
				.signWith(key)
				.subject(user.getEmail())
				.expiration(expiration)
				.claims(claims)
				.compact();
		
		return new TokenResponse(token);
	}
	
	private Date generateExpirationDate() {
		long expirationMinutes = 60;
		LocalDateTime now = LocalDateTime.now().plusMinutes(expirationMinutes);
		return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private Map<String, Object> generateTokenClaims(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("name", user.getName());
		return claims;
	}
	
	public String getEmailFromToken(String token) {
		
		try {
			JwtParser jwtParser = Jwts.parser().verifyWith(this.keyGenerator.getKey()).build();
			return jwtParser.parseSignedClaims(token)
					.getPayload().getSubject();			
		} catch (JwtException e) {
			throw new InvalidTokenException("Invalid Token: Cause: " +  e.getMessage());
		}
		
	}
}
