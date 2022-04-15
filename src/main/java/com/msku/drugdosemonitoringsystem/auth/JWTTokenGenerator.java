package com.msku.drugdosemonitoringsystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTTokenGenerator   {

    @Value("${ilacdozutakipsistemi.application.key}")
    private String SECRET_KEY;


    @Value("${ilacdozutakipsistemi.application.expiretime}")
    private long EXPIRE_TIME;

    public Claims parseToken(String token){
        return  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String createToken(Authentication auth){
        UserDetail patientDetail = (UserDetail) auth.getPrincipal();
        Date expireIn = new Date(new Date().getTime()+EXPIRE_TIME);
        return Jwts.builder().setSubject((patientDetail.getId().toString())).setIssuedAt(new Date()).setExpiration(expireIn).signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }


    UUID getUserId(String token){
        Claims claims = parseToken(token);
        return UUID.fromString(claims.getSubject());
    }

    boolean isTokenValid(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch (Exception e){
            return false;
        }
    }

    boolean isTokenExpired(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }



}
