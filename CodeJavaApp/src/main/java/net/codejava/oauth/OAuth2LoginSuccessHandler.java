package net.codejava.oauth;

import net.codejava.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository repo;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getFullName();


        User existUser = repo.findByEmail(email);
        //dobbiamo trovare se la mail di oauth è già dentro il db
        if(existUser ==  null){
            //registrare nuovo utente
            customUserDetailsService.createNewCustomerAfterOAuthSuccess(email,name, AuthenticationProvider.GOOGLE);
        }

        System.out.println(email);
        System.out.println(name);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
