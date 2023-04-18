package cpt202.project.pizzaorderingsys.config;

import cpt202.project.pizzaorderingsys.security.SecurityUserDetailsService;
import cpt202.project.pizzaorderingsys.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired private SecurityUserDetailsService userDetailsService;
    @Autowired private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


//  @Autowired private AttemptsRepository attemptsRepository;

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
            String username = authentication.getName();
            System.out.println("authenticate username: " + username);
            String password = authentication.getCredentials().toString();
            System.out.println("password: "+password);

            UserDetails u = userDetailsService.loadUserByUsername(username);
            System.out.println(u.getPassword());
            System.out.println(passwordEncoder.getClass());
            if (passwordEncoder.matches(password, u.getPassword())) {
                //如果密码匹配，则返回Authentication接口的实现以及必要的详细信息
                System.out.println("authenticate successfully");
                return new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
            } else {	//密码不匹配，抛出异常
                System.out.println("error_authenticate password " + password);
                throw new BadCredentialsException("Something went wrong!");
            }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }





}
