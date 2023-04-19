package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepo extends JpaRepository<Order, Long> {

    Iterable<Order> findByCustomerName(String customerName);

    Iterable<Order> findByCustomerPhone(String customerPhone);

    Iterable<Order> findByCustomerAddress(String customerAddress);

    Iterable<Order> findByOrderStatus(String orderStatus);

    Iterable<Order> findByPayMethod(String payMethod);

    Iterable<Order> findByOrderTime(String orderTime);

    Iterable<Order> findByUpdateTime(String updateTime);

    Iterable<Order> findByOrderId(Long orderId);

//    Iterable<Order> findByOrderDetailId(Long orderDetailId);



//    Iterable<Order> findByPizzaSize(String pizzaSize);

//    Iterable<Order> findByPizzaTopping(String pizzaTopping);

//    Iterable<Order> findByPizzaPrice(String pizzaPrice);

//    Iterable<Order> findByQuantity(int quantity);

    Iterable<Order> findByTotalPrice(double totalPrice);

    Iterable<Order> findByOrderRemark(String orderRemark);

}