package br.com.morgado.digitalaccount.api.domain.authentication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;

@Service
public class TokenService {

    public String generateToken(UserModel user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("Digital Account")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirest(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // TODO create custom exception
            throw new InternalError("Error generating JWT access token.");
        }
    }

    public String generateRefreshToken(UserModel user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("Digital Account")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(expirest(60))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // TODO create custom exception
            throw new InternalError("Error generating JWT access refresh token.");
        }
    }

    private Instant expirest(Integer minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Digital Account")
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            // TODO create custom exception
            throw new InternalError("Invalid signature/claims.");
        }
    }

}
