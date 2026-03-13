package me.gabriel.authentication.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import me.gabriel.authentication.model.UserModel;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(UserModel userModel) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
            .withIssuer("authentication-api")
            .withSubject(userModel.getUsername())
            .withExpiresAt(generateExpirationTime())
            .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
            .withIssuer("authentication-api")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error while validating token", e);
        }
    }

    public LocalDateTime getExpirationTime(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant expiresAt = JWT.require(algorithm)
            .withIssuer("authentication-api")
            .build()
            .verify(token)
            .getExpiresAt().toInstant();

            return LocalDateTime.ofInstant(expiresAt, ZoneOffset.of("-03:00"));
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error while getting expiration time.", e);
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
