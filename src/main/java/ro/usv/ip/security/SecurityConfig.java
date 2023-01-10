package ro.usv.ip.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import ro.usv.ip.model.Role;
import ro.usv.ip.security.filters.TokenAuthenticationFilter;
import ro.usv.ip.security.filters.UsernamePasswordAuthFilter;
import ro.usv.ip.security.providers.TokenAuthenticationProvider;
import ro.usv.ip.security.providers.UsernamePasswordAuthProvider;

import java.util.List;

@Configuration
public class SecurityConfig {
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    public SecurityConfig(TokenAuthenticationProvider tokenAuthenticationProvider, UsernamePasswordAuthProvider usernamePasswordAuthProvider) {
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public UsernamePasswordAuthFilter usernamePasswordAuthFilter() {
        return new UsernamePasswordAuthFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, @Value("${security.allowed.paths}") final String[] paths) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);

        //this is for enable h2 console
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.authorizeRequests().mvcMatchers(
                        paths
                ).permitAll()
                .anyRequest().hasAnyAuthority(Role.ADMIN.name(), Role.CONTENT_CREATOR.name());


        httpSecurity.cors(c -> {
            CorsConfigurationSource cc = r -> {
                CorsConfiguration cs = new CorsConfiguration();
                cs.setAllowedOrigins(List.of("*"));
                cs.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
                cs.setAllowedHeaders(List.of("*"));
                return cs;
            };

            c.configurationSource(cc);
        });

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Bean
    public AuthenticationManager providerManager() {
        return new ProviderManager(tokenAuthenticationProvider, usernamePasswordAuthProvider);
    }


}
