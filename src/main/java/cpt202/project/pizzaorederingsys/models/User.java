package cpt202.project.pizzaorederingsys.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.List.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "undefined")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username")
    protected String userName;

    @Column(name = "password")
    protected String password;

    @Convert(converter = GrantedAuthoritiesConverter.class)
    private List<GrantedAuthority> authorities;

    @Transient
    private boolean accountNonLocked;


    public User(String userName, String password,
                boolean accountNonLocked,List<GrantedAuthority> authorities) {
        this.userName = userName;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }

    public User(String userName, String password,
                boolean accountNonLocked) {
        this.userName = userName;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
    }

    public User() {}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean getAccountNonLocked() {
        return accountNonLocked;
    }



    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
