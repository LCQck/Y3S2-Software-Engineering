package cpt202.project.pizzaorederingsys.models;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@DiscriminatorValue(value = "customer")
public class Customer extends User{


    public Customer() {
        super();
    }

    public Customer( String userName, String password) {
        super(userName, password);
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
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
}
