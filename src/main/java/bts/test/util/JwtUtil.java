package bts.test.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class JwtUtil {
    public static final String AUTHORIZATION = "Authorization";

    public static String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
