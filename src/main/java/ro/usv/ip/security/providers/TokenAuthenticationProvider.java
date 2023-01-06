package ro.usv.ip.security.providers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ro.usv.ip.security.authentication.JwtAuthenticationToken;
import ro.usv.ip.security.utility.CustomUserDetailsService;
import ro.usv.ip.security.utility.JwtUtility;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtility jwtUtility;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        String email;
        try {
            email = jwtUtility.getEmailFromToken(token);
        } catch (IllegalArgumentException | ExpiredJwtException e) {
            throw new JwtException("Authentication error");
        }
        if (email == null) {
            throw new JwtException("Authentication error");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (!jwtUtility.validateToken((String) authentication.getCredentials(), userDetails)) {
            throw new JwtException("Authentication error");
        }
        return new JwtAuthenticationToken(userDetails.getAuthorities(), userDetails, (String) authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.equals(authentication);
    }
}
