package com.example.NetChatBackend.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenProvider {
	
	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken( Authentication authentication ){
		String jwt = Jwts.builder().setIssuer("Code with Zosh")
				.setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 864000000))
				.claim("email",authentication.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public String getEmailFromToken(String jwt){
		jwt = jwt.substring(7);     //jwt = Token
		Claims claim = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claim.get("email"));
		
		return email;
	}
}
