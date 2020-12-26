package by.cniitu.chargepoint.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

    public static Claims getClaims(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("chargePoint".getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return claims;
    }

}
