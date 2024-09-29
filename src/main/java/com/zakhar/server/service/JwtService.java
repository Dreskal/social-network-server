package com.zakhar.server.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SALT = "b31b4a75f21e8266cc82bb9713041467a8e2b0217b73ec1ebc01c33b3a561567";

    public String extractUsername(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);

    }

    public String generateToken(UserDetails details){
        return generateToken(new HashMap<>(), details);
    }

    public String generateToken (Map<String, Object> exstraClaim, UserDetails details) {
        return Jwts
                .builder()
                .setClaims(exstraClaim)
                .setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSingingKey(), SignatureAlgorithm.HS256)
                .compact();  //добавить еще *60
    }

    public  boolean isTokenValide(String token, UserDetails details){
        final String userName = extractUsername(token);
        return (userName.equals(details.getUsername())) && (!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return  extractExpirition(token).before(new Date());
    }

    private Date extractExpirition(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSingingKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SALT);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
