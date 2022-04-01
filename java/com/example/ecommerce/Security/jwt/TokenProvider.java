package com.example.ecommerce.Security.jwt;


import com.example.ecommerce.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private final MyWayProperties mapiItProperties;

    private static final String USER_ID_KEY = "userId";
    private static final String USER_LOGIN_KEY = "login";
    private static final String USER_NAME_KEY = "userName";
    private static final String USER_PAYS = "pays";

    public TokenProvider(MyWayProperties mapiItProperties) {
        this.mapiItProperties = mapiItProperties;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        String secret = mapiItProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!StringUtils.isEmpty(secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(mapiItProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
                10000 * mapiItProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                10000 * mapiItProperties.getSecurity().getAuthentication().getJwt()
                        .getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, boolean rememberMe, UUID userId, String login, String userName) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(USER_ID_KEY, userId)
                .claim(USER_LOGIN_KEY, login)
                .claim(USER_NAME_KEY, userName)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UUID userId = null;
        String login = null;
        String userName = null;
        String lang = null;
        String pays = null;
        Object userIdObj = claims.get(USER_ID_KEY);
        Object userNameObj = claims.get(USER_NAME_KEY);
        Object userLoginObj = claims.get(USER_LOGIN_KEY);
        Object userPaysObj = claims.get(USER_PAYS);

        if (userIdObj != null) {
            String userIdStr = userIdObj.toString();
            userId = UUID.fromString(userIdStr);
            log.debug("Claim--> {}", userId);
        } else {
            log.debug("No userId in token");
        }

        if (userNameObj != null) {
            userName = userNameObj.toString();
            log.debug("Claim--> {}", userName);
        } else {
            log.debug("No userName in token");
        }


        if (userLoginObj != null) {

            login = userLoginObj.toString();
            log.debug("Claim--> {}", login);
        } else {
            log.debug("No login id in token");
        }



        if (userPaysObj != null) {
            //  user.setTenant(new Tenant(schema));

            pays = userPaysObj.toString();
            log.debug("Claim--> {}", pays);
        } else {
            log.debug("No pays name in token");
        }

        User principal = new User(claims.getSubject(), "", authorities);
        return (Authentication) new UsernameAndPasswordAuthenticationRequest(token, authorities, userId, login, userName, lang, pays);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
