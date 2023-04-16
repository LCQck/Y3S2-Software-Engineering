package cpt202.project.pizzaorderingsys.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "shopmanager")
@DiscriminatorValue(value = "shop_manager")
public class ShopManager extends User{

    @Column(name = "description")
    private String description;

    //Constructor
    public ShopManager(String userName, String password, boolean accountNonlocked,
                       String description,
                       List<GrantedAuthority> authorityList) {
        super(userName, password,accountNonlocked,authorityList);
        this.description = description;

    }
    public ShopManager(String userName, String password, boolean accountNonlocked,
                       String description) {
        super(userName, password,accountNonlocked);
        this.description = description;

    }

    public ShopManager() {
        super();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SHOP_MANAGER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        this.setAuthorities(authorities);
    }

    public Long getShopMangId() {
        return id;
    }

    public void setShopMangId(Long id) {
        this.id = id;
    }

    public String getShopMangUsername() {
        return userName;
    }

    public void setShopMangUsername(String userName) {
        this.userName = userName;
    }

    public String getShopMangPassword() {
        return password;
    }

    public void setShopMangPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
