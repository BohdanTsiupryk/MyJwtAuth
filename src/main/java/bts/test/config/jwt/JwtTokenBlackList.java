package bts.test.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenBlackList {
    private Set<Token> blackList = new HashSet<>();

    public void addToken(String token, Date expirationDate) {
        blackList.add(new Token(token, expirationDate));
    }

    public boolean isTokenInBlackList(String token) {
        return blackList.stream()
                .anyMatch(blockedToken -> blockedToken.getToken().equals(token));
    }

    public void checkAndDeleteExpToken() {
        Date now = Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC));
        Set<Token> toDelete = blackList.stream()
                .filter(token -> token.getExpirationDate().after(now))
                .collect(Collectors.toSet());
        blackList.removeAll(toDelete);
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    private static class Token {
        @Setter
        @Getter
        private String token;
        @Setter
        @Getter
        private Date expirationDate;
    }
}
