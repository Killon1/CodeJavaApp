package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public void createNewCustomerAfterOAuthSuccess(String email, String name, AuthenticationProvider provider) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(name);
        user.setAuthProvider(provider);

        repo.save(user);
    }
}
