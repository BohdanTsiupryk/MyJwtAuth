package bts.test.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtTokenBlackList blackList;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expirationDay:1}")
    private String jwtExpDay;

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(Integer.parseInt(jwtExpDay)).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        if (blackList.isTokenInBlackList(token)) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("invalid token");
        }
        return false;
    }

    public void invalidateToken(HttpServletRequest httpRequest) {
        String tokenFromRequest = JwtUtil.getTokenFromRequest(httpRequest);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(tokenFromRequest);
        Date expiration = claimsJws.getBody().getExpiration();
        blackList.addToken(tokenFromRequest, expiration);
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}