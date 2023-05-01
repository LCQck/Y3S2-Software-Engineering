package cpt202.project.pizzaorderingsys.models;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

//one to many
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @Column(name = "shopping_cart_id")
    private Long id;
    @OneToOne
    private Customer customerId;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "")
    private  Double totalPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<ShoppingCartDetails> shoppingCartDetailsList;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id,
                        //Long customerId,
                        Double totalPrice) {
        this.id = id;
        //this.customerId = customerId;
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public List<ShoppingCartDetails> getShoppingCartDetailsList() {
        return shoppingCartDetailsList;
    }

    public void setShoppingCartDetailsList(List<ShoppingCartDetails> shoppingCartDetailsList) {
        this.shoppingCartDetailsList = shoppingCartDetailsList;
    }

    public void setEmptyShoppingCartDetailsList(){
        this.shoppingCartDetailsList = new ArrayList<>();
    }

    public  void addShoppingCartDetail(ShoppingCartDetails shoppingCartDetail){
        this.shoppingCartDetailsList.add(shoppingCartDetail);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
//one to many