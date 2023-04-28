package cpt202.project.pizzaorderingsys.models;

import javax.persistence.*;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Integer id;
    @Column(name = "pizza_name")
    private String name;
    @Column(name = "base_price")
    private int price;

    private String description;

    private int discount;
    private int state;

    private String image;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "label_id")
    private Category category;


    public Pizza(){
    }

    public Pizza(int id, String name, int price, int discount, int state, String description, String image){
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.state = state;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", discount=" + discount + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryName() {
        return category.getLabel();
    }

    public void setCategory(Category category) {

        this.category = category;
        if (!category.getPizzaInCategory().contains(this)) {
            category.addInCategory(this);
        }
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
