package cpt202.project.pizzaorderingsys.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@DiscriminatorValue(value = "customer")
public class Customer extends User{

    @Column(name = "name")
    private String nickname;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Address> addressList;

    public Customer() {
        super();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        this.setAuthorities(authorities);
    }

    public Customer(String userName, String password,
                    boolean accountNonlocked, String nickname) {
        super(userName, password,accountNonlocked);
        this.nickname = nickname;
    }

    public Customer(String userName, String password,
                    boolean accountNonlocked, String nickname,
                    List<GrantedAuthority> grantedAuthority) {
        super(userName, password,accountNonlocked,grantedAuthority);
        this.nickname = nickname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerUsername() {
        return userName;
    }

    public void setCustomerUsername(String userName) {
        this.userName = userName;
    }

    public String getCustomerPassword() {
        return password;
    }

    public void setCustomerPassword(String password) {
        this.password = password;
    }

}
