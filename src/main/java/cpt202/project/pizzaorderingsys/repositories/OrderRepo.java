package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<List<Order>> findByCustomerName(String customerName);

    Optional<List<Order>> findAllByCustomer(Customer customer);

    Iterable<Order> findByCustomerPhone(String customerPhone);

    Iterable<Order> findByCustomerAddress(String customerAddress);

    Optional<List<Order>> findAllByOrderStatus(OrderStatus orderStatus);

    Iterable<Order> findByPayMethod(String payMethod);

    Iterable<Order> findByOrderTime(String orderTime);

    Iterable<Order> findByUpdateTime(String updateTime);

    Optional<Order> findById(Long orderId);

//    Iterable<Order> findByOrderDetailId(Long orderDetailId);



//    Iterable<Order> findByPizzaSize(String pizzaSize);

//    Iterable<Order> findByPizzaTopping(String pizzaTopping);

//    Iterable<Order> findByPizzaPrice(String pizzaPrice);

//    Iterable<Order> findByQuantity(int quantity);

    Iterable<Order> findByTotalPrice(double totalPrice);

    Iterable<Order> findByOrderRemark(String orderRemark);

    @Modifying
    @Query(value = "update Order o set o.orderStatus = ?2 where o.Id = ?1")
    void updateOrderStatus(Long orderId, OrderStatus orderStatus);

    @Modifying
    @Query(value = "DELETE FROM order WHERE order.id=:id", nativeQuery=true)
    void deleteById(@Param("id") int id);

    @Modifying
    @Query("update Order o set o.comment = ?1 where o.Id = ?2")
    void updateOrderComment(String comment, Long Id);

    @Modifying
    @Query("update Order o set o.customerComment = ?1 where o.Id = ?2")
    void updateCustomerOrderComment(String customerComment, Long Id);

    @Modifying
    @Query("update Order o set o.orderStatus = ?1 where o.Id = ?2")
    void cancelOrder(OrderStatus orderStatus, Long Id);

}