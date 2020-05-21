package edadamanager.controller;

import edadamanager.model.User;
import edadamanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        User user = repository.findByUsername(token.getName());
        if (user==null || !user.getPassword().equalsIgnoreCase(token.getCredentials().toString())){
            throw new BadCredentialsException("No user or password");
        }
        return new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());
    }

    //    говорит, что пользователь авторизуется через имя и пароль
    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
