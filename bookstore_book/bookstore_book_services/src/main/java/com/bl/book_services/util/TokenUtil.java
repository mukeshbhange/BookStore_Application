package com.bl.book_services.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {

	public final String TOKEN_SECRET = "secreteKey";
	
	public String createToken(Long id) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

			String token = JWT.create().withClaim("emp_id", id).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long decodeToken(String token) {
		Long userid;
		Verification verification = null;
		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		JWTVerifier jwtverifier = verification.build();
		DecodedJWT decodedjwt = jwtverifier.verify(token);

		Claim claim = decodedjwt.getClaim("emp_id");
		userid = claim.asLong();
		return userid;
	}

}
