package cpt202.project.pizzaorderingsys.models;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

//many to one
@Entity
@Table(name = "shopping_cart_details")
public class ShoppingCartDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_detail_id")
    private Long id;

    private String pizzaName;
    @OneToOne
    private Size size;
    @OneToOne
    private Taste taste;

    private Integer count;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public ShoppingCartDetails() {

    }



    public ShoppingCartDetails(Long shoppingCartId, String pizzaName,
                               Size size, Taste taste, Integer count, Double price) {
        this.id = shoppingCartId;
        this.pizzaName = pizzaName;
        this.size = size;
        this.taste = taste;
        this.count = count;
        this.price = price;
    }


    public ShoppingCart getShoppingCart() {return this.shoppingCart;}

    public void setShoppingCart(ShoppingCart shoppingCartId) {this.shoppingCart = shoppingCartId;}

    public String getPizzaName() {return pizzaName;}

    public void setPizzaName(String pizzaName) {this.pizzaName = pizzaName;}

    public Size getSize() {return size;}

    public void setSize(Size size) {this.size = size;}

    public Taste getTaste() {return taste;}

    public void setTaste(Taste taste) {this.taste = taste;}

    public Integer getCount() {return count;}

    public void setCount(Integer count) {this.count = count;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}
}
