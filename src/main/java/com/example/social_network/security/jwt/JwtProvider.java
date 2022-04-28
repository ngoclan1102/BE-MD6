package com.example.social_network.security.jwt;


import com.example.social_network.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger( JwtProvider.class);
    private String jwtSecret = "tu.pham";
    private int jwtExpiration = 86400;

    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date( new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken( String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        }
        catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        }
        catch (MalformedJwtException e) {
            logger.error("Malformed JWT token -> Message: {}", e);
        }
        catch (IllegalArgumentException e) {
            logger.error("IllegalArgument JWT token -> Message: {}", e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
