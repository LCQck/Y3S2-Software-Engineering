package cpt202.project.pizzaorderingsys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/pizzaOrderingSys/sendVerCode")
                .permitAll()
                .antMatchers("/pizzaOrderingSys/register**")
                .permitAll()
                .antMatchers("/pizzaOrderingSys/forgetPassword**")
                .permitAll()
                .antMatchers("/pizzaOrderingSys/shopmanager***").hasAuthority("ROLE_SHOP_MANAGER")
                .antMatchers("/pizzaOrderingSys/customer***").hasAuthority("ROLE_CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin() .loginPage("/pizzaOrderingSys/login").defaultSuccessUrl("/pizzaOrderingSys/index")
                .permitAll()
                .and()
                .logout() .invalidateHttpSession(true)
                .clearAuthentication(true) .permitAll();


    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
        System.out.println("AuthenticationManagerBuilder auth: "+auth);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}

