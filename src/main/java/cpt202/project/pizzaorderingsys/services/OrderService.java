package cpt202.project.pizzaorderingsys.services;

// Add an order service containing the following methods:
// 1. Add an order
// 2. Update an order
// 3. Delete an order
// 4. Get all orders
// 5. Get an order by order id
// 6. Get an order by username
// 7. Get an order by user phone
// 8. Get an order by user address
// 9. Get an order by order status

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public void addOrder(Order order) {
        orderRepo.save(order);
    }

    public void updateOrder(Order order) {
        orderRepo.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }

    public Iterable<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Iterable<Order> getOrderByOrderId(Long orderId) {
        return orderRepo.findByOrderId(orderId);
    }

    public Iterable<Order> getOrderByCustomerName(String customerName) {
        return orderRepo.findByCustomerName(customerName);
    }

    public Iterable<Order> getOrderByCustomerPhone(String customerPhone) {
        return orderRepo.findByCustomerPhone(customerPhone);
    }

    public Iterable<Order> getOrderByCustomerAddress(String customerAddress) {
        return orderRepo.findByCustomerAddress(customerAddress);
    }

    public Iterable<Order> getOrderByOrderStatus(String orderStatus) {
        return orderRepo.findByOrderStatus(orderStatus);
    }

    public Iterable<Order> getOrderByPayMethod(String payMethod) {
        return orderRepo.findByPayMethod(payMethod);
    }

    public Iterable<Order> getOrderByOrderTime(String orderTime) {
        return orderRepo.findByOrderTime(orderTime);
    }

    public Iterable<Order> getOrderByUpdateTime(String updateTime) {
        return orderRepo.findByUpdateTime(updateTime);
    }

    public Iterable<Order> getOrderByTotalPrice(double totalPrice) {
        return orderRepo.findByTotalPrice(totalPrice);
    }

    public Iterable<Order> getOrderByOrderRemark(String orderRemark) {
        return orderRepo.findByOrderRemark(orderRemark);
    }

//    public Iterable<Order> getOrderByOrderDetailId(Long orderDetailId) {
//        return orderRepo.findByOrderDetailId(orderDetailId);
//    }
//
//    public Iterable<Order> getOrderByPizzaImage(String pizzaImage) {
//        return orderRepo.findByPizzaImage(pizzaImage);
//    }
//
//    public Iterable<Order> getOrderByPizzaName(String pizzaName) {
//        return orderRepo.findByPizzaName(pizzaName);
//    }

//    public Iterable<Order> getOrderByPizzaSize(String pizzaSize) {
//        return orderRepo.findByPizzaSize(pizzaSize);
//    }
//
//    public Iterable<Order> getOrderByPizzaTopping(String pizzaTopping) {
//        return orderRepo.findByPizzaTopping(pizzaTopping);
//    }
//
//    public Iterable<Order> getOrderByPizzaPrice(String pizzaPrice) {
//        return orderRepo.findByPizzaPrice(pizzaPrice);
//    }

//    public Iterable<Order> getOrderByQuantity(int quantity) {
//        return orderRepo.findByQuantity(quantity);
//    }

}