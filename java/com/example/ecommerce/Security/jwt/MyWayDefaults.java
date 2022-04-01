package com.example.ecommerce.Security.jwt;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyWayDefaults {


    public interface Gateway {
        Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap<>();

        public interface RateLimiting {
            boolean enabled = false;
            long limit = 100000L;
            int durationInSeconds = 3600;
        }
    }

    public interface Security {
        public interface RememberMe {
            String key = null;
        }

        public interface Authentication {
            public interface Jwt {
                String secret = "{noop}my-secret-key-which-should-be-changed-in-production-and-be-base64-encoded";
                String base64Secret = "OTk5ZGY2YzgwNmU0M2M2NjM3ZDBjY2M4NjBlYmNhZDA3ZmY4YzM4YWNjMGIzOTI3ZjllNmE1Y2E3NTUzZjE4MTExY2M4MDRjMzY4YWM0M2FhNWI4OWJhMzdlZGRkNGNkYjUxYTA3YTdhZDJlYmYzOGQ1MDA2ODhmNDI3NTRkZjc";
                long tokenValidityInSeconds = 1800L;
                long tokenValidityInSecondsForRememberMe = 2592000L;
            }
        }

        public interface ClientAuthorization {
            String accessTokenUri = null;
            String tokenServiceId = null;
            String clientId = null;
            String clientSecret = null;
        }
    }
}
