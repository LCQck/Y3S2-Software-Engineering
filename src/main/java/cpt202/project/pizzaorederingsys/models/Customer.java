package cpt202.project.pizzaorederingsys.models;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@DiscriminatorValue(value = "customer")
public class Customer extends User{

    @Column(name = "name")
    private String name;

    public Customer() {
        super();
    }

    @Override
    public String getUsername() {
        return userName;
    }

//    public Customer( String userName, String password) {
//        super(userName, password);
//    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }



    public String getCusUserName() {
        return userName;
    }


    public void setCusUserName(String userName) {
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
