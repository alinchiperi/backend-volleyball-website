package ro.usv.ip.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.usv.ip.security.model.JwtResponse;
import ro.usv.ip.security.utility.CustomUserDetailsService;
import ro.usv.ip.security.utility.JwtUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtUtility jwtUtility;
    @Autowired
    private  CustomUserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/api/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email = request.getHeader("email");
        String password = request.getHeader("password");
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        final String token = jwtUtility.generateToken(userDetails);
        JwtResponse tokenResponse = new JwtResponse(token);
        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
        response.getWriter().write(String.valueOf(tokenResponse));
        response.getWriter().flush();
        auth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
