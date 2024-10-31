package com.quefaire.demo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.forSigningKey;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;  // Clé secrète pour signer le JWT
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;  // Expiration du token

    private Key key;

    @PostConstruct
    public void init() {
        // Utilise la clé secrète pour générer un objet Key compatible
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /***
     * generates a jwt token for user necessary for authentication and authorization
     * @param username
     * @return
     * function called when a user's authentication succeeds
     */
    public String generateJwtToken(String username, Map<String, Object> otherClaims) {
        return Jwts.builder()
                .subject(username)
                .claims(otherClaims)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key, forSigningKey(key))
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(token).build().parseSignedClaims(token).getBody();
    }

    public boolean validateJwtToken(String token) {
        return true;
    }

}
