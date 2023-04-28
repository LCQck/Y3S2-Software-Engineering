package cpt202.project.pizzaorderingsys.models;

import com.alibaba.fastjson2.annotation.JSONField;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")

public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;
    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Order order;

    @Column(name = "pizza_image")
    private String pizzaImage;

    @JoinColumn(name = "pizza_name")
    private String pizzaName;

    @Column(name = "pizza_size")
    private String pizzaSize;

    @Column(name = "pizza_topping")
    private String pizzaTopping;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "pizza_price")
    private String pizzaPrice;

    public OrderDetail() {
    }

    public OrderDetail(Order order, String pizzaImage, String pizzaName, String pizzaSize, String pizzaTopping, String pizzaPrice, int quantity) {
        this.order = order;
        this.pizzaImage = pizzaImage;
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.pizzaTopping = pizzaTopping;
        this.pizzaPrice = pizzaPrice;
        this.quantity = quantity;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPizzaImage() {
        return pizzaImage;
    }

    public void setPizzaImage(String pizzaImage) {
        this.pizzaImage = pizzaImage;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getPizzaTopping() {
        return pizzaTopping;
    }

    public void setPizzaTopping(String pizzaTopping) {
        this.pizzaTopping = pizzaTopping;
    }

    public String getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(String pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailId=" + orderDetailId +
                ", order=" + order +
                ", pizzaImage='" + pizzaImage + '\'' +
                ", pizzaName='" + pizzaName + '\'' +
                ", pizzaSize='" + pizzaSize + '\'' +
                ", pizzaTopping='" + pizzaTopping + '\'' +
                ", pizzaPrice='" + pizzaPrice + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
