package ro.usv.ip.security.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.usv.ip.model.Administrator;
import ro.usv.ip.repository.AdministratorRepository;
import ro.usv.ip.security.model.AdministratorSecurityDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdministratorRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrator administrator = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new AdministratorSecurityDetails(administrator);
    }
}
