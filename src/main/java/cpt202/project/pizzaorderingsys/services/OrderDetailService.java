package cpt202.project.pizzaorderingsys.services;

//Add an order detail service containing the following methods:
//1. Add an order detail
//2. Delete an order detail
//3. Get all order details
//4. Get all order detail by all order id
//5. Get an order detail by order detail id

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderDetail;
import cpt202.project.pizzaorderingsys.repositories.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
    }

    public void deleteOrderDetail(Long orderDetailId) {
        orderDetailRepo.deleteById(orderDetailId);
    }

    public Iterable<OrderDetail> getAllOrderDetails() {
        return orderDetailRepo.findAll();
    }

    public List<OrderDetail> getOrderDetailByOrderId(Order orderId) {
        return orderDetailRepo.findAllByOrder(orderId);
    }

    public OrderDetail getOrderDetailByOrderDetailId(Long orderDetailId) {
        return orderDetailRepo.findByOrderDetailId(orderDetailId);
    }

    public OrderDetail getOrderByOrderDetailId(Long orderDetailId) {
        return orderDetailRepo.findByOrderDetailId(orderDetailId);
    }

    public OrderDetail getOrderByPizzaImage(String pizzaImage) {
        if (orderDetailRepo.findByPizzaImage(pizzaImage).isPresent())
            return orderDetailRepo.findByPizzaImage(pizzaImage).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

    public OrderDetail getOrderByPizzaName(String pizzaName) {
        if (orderDetailRepo.findByPizzaName(pizzaName).isPresent())
            return orderDetailRepo.findByPizzaName(pizzaName).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

    public OrderDetail getOrderByPizzaSize(String pizzaSize) {
        if(orderDetailRepo.findByPizzaSize(pizzaSize).isPresent())
            return orderDetailRepo.findByPizzaSize(pizzaSize).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

    public OrderDetail getOrderByPizzaTopping(String pizzaTopping) {
        if(orderDetailRepo.findByPizzaTopping(pizzaTopping).isPresent())
            return orderDetailRepo.findByPizzaTopping(pizzaTopping).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

    public OrderDetail getOrderByPizzaPrice(String pizzaPrice) {
        if(orderDetailRepo.findByPizzaPrice(pizzaPrice).isPresent())
            return orderDetailRepo.findByPizzaPrice(pizzaPrice).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

    public OrderDetail getOrderByQuantity(int quantity) {
        if(orderDetailRepo.findByQuantity(quantity).isPresent())
            return orderDetailRepo.findByQuantity(quantity).get();
        else
            throw new NullPointerException("OrderDetail not found");
    }

}
