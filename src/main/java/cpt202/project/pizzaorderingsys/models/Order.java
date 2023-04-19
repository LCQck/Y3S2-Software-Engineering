package cpt202.project.pizzaorderingsys.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Set up an "order_master" table in the database representing the Order class

@Entity
@Table(name = "order_master")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    //@JoinColumn(name = "order_detail_id")
    private List<OrderDetail> orderDetail;

    @Column(name = "order_id")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_method")
    private PayMethod payMethod;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "order_remark")
    private String orderRemark;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    public Order() {
    }

    public Order(Long orderId, OrderStatus orderStatus, Customer customer, Date orderTime, Date updateTime, PayMethod payMethod, double totalPrice, String orderRemark, String customerName, String customerPhone, String customerAddress) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.orderTime = orderTime;
        this.updateTime = updateTime;
        this.payMethod = payMethod;
        this.totalPrice = totalPrice;
        this.orderRemark = orderRemark;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", orderTime=" + orderTime +
                ", updateTime=" + updateTime +
                ", payMethod=" + payMethod +
                ", totalPrice=" + totalPrice +
                ", orderRemark='" + orderRemark + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }

}