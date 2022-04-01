package com.example.ecommerce.Security.jwt;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(
        prefix = "maps-it",
        ignoreUnknownFields = false
)
public class MyWayProperties {


    private final Security security = new Security();
    private final CorsConfiguration cors = new CorsConfiguration();
    private final Gateway gateway = new Gateway();
    private final Mail mail = new Mail();

    public MyWayProperties() {
    }

    public Security getSecurity() {
        return this.security;
    }
    public CorsConfiguration getCors() {
        return this.cors;
    }
    public Gateway getGateway() {
        return this.gateway;
    }
    public Mail getMail() {
        return this.mail;
    }

    public static class Gateway {
        private final RateLimiting rateLimiting = new RateLimiting();
        private Map<String, List<String>> authorizedMicroservicesEndpoints;

        public Gateway() {
            this.authorizedMicroservicesEndpoints = MyWayDefaults.Gateway.authorizedMicroservicesEndpoints;
        }

        public RateLimiting getRateLimiting() {
            return this.rateLimiting;
        }

        public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
            return this.authorizedMicroservicesEndpoints;
        }

        public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints) {
            this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
        }

        public static class RateLimiting {
            private boolean enabled = false;
            private long limit = 100000L;
            private int durationInSeconds = 3600;

            public RateLimiting() {
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getLimit() {
                return this.limit;
            }

            public void setLimit(long limit) {
                this.limit = limit;
            }

            public int getDurationInSeconds() {
                return this.durationInSeconds;
            }

            public void setDurationInSeconds(int durationInSeconds) {
                this.durationInSeconds = durationInSeconds;
            }
        }
    }

    public static class Mail {
        private boolean enabled = false;
        private String from = "";
        private String baseUrl = "";
        private String pwd="";

        public Mail() {
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getFrom() {
            return this.from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getPwd() {
            return this.pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getBaseUrl() {
            return this.baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }


    public static class Security {
        private final ClientAuthorization clientAuthorization = new ClientAuthorization();
        private final Authentication authentication = new Authentication();
        private final RememberMe rememberMe = new RememberMe();
        private final OAuth2 oauth2 = new OAuth2();

        public Security() {
        }

        public ClientAuthorization getClientAuthorization() {
            return this.clientAuthorization;
        }

        public Authentication getAuthentication() {
            return this.authentication;
        }

        public RememberMe getRememberMe() {
            return this.rememberMe;
        }

        public OAuth2 getOauth2() {
            return this.oauth2;
        }

        public static class OAuth2 {
            private List<String> audience = new ArrayList<>();

            public OAuth2() {
            }

            public List<String> getAudience() {
                return Collections.unmodifiableList(this.audience);
            }

            public void setAudience(@NotNull List<String> audience) {
                this.audience.addAll(audience);
            }
        }

        public static class RememberMe {
            @NotNull
            private String key;

            public RememberMe() {
                this.key = MyWayDefaults.Security.RememberMe.key;
            }

            public String getKey() {
                return this.key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public static class Authentication {
            private final Jwt jwt = new Jwt();

            public Authentication() {
            }

            public Jwt getJwt() {
                return this.jwt;
            }

            public static class Jwt {
                private String secret;
                private String base64Secret;
                private long tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe;

                public Jwt() {
                    this.secret = MyWayDefaults.Security.Authentication.Jwt.secret;
                    this.base64Secret = MyWayDefaults.Security.Authentication.Jwt.base64Secret;
                    this.tokenValidityInSeconds = 4320L;
                    this.tokenValidityInSecondsForRememberMe = 4320L;
                }

                public String getSecret() {
                    return this.secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public String getBase64Secret() {
                    return this.base64Secret;
                }

                public void setBase64Secret(String base64Secret) {
                    this.base64Secret = base64Secret;
                }

                public long getTokenValidityInSeconds() {
                    return this.tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return this.tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }

        public static class ClientAuthorization {
            private String accessTokenUri;
            private String tokenServiceId;
            private String clientId;
            private String clientSecret;

            public ClientAuthorization() {
                this.accessTokenUri = MyWayDefaults.Security.ClientAuthorization.accessTokenUri;
                this.tokenServiceId = MyWayDefaults.Security.ClientAuthorization.tokenServiceId;
                this.clientId = MyWayDefaults.Security.ClientAuthorization.clientId;
                this.clientSecret = MyWayDefaults.Security.ClientAuthorization.clientSecret;
            }

            public String getAccessTokenUri() {
                return this.accessTokenUri;
            }

            public void setAccessTokenUri(String accessTokenUri) {
                this.accessTokenUri = accessTokenUri;
            }

            public String getTokenServiceId() {
                return this.tokenServiceId;
            }

            public void setTokenServiceId(String tokenServiceId) {
                this.tokenServiceId = tokenServiceId;
            }

            public String getClientId() {
                return this.clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getClientSecret() {
                return this.clientSecret;
            }

            public void setClientSecret(String clientSecret) {
                this.clientSecret = clientSecret;
            }
        }
    }
}
