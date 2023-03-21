package cpt202.project.pizzaorederingsys.models;

import javax.persistence.*;


@Entity
@Table(name = "shopmanager")
@DiscriminatorValue(value = "shop_manager")
public class ShopManager extends User{

    @Column(name = "description")
    private String description;

    //Constructor
    public ShopManager(String userName, String password) {
        super(userName, password);
    }

    public ShopManager() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
