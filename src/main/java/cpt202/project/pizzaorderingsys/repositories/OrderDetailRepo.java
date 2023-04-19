package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

// Add an order detail repository containing the following methods:
// 1. Get all order details by order id
// 2. Get an order detail by order detail id

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findAllByOrder(Order orderId);

    OrderDetail findByOrderDetailId(Long orderDetailId);

    Optional<OrderDetail> findByPizzaSize(String pizzaSize);

    Optional<OrderDetail> findByPizzaTopping(String pizzaTopping);

    Optional<OrderDetail> findByPizzaPrice(String pizzaPrice);

    Optional<OrderDetail> findByQuantity(int quantity);

    Optional<OrderDetail> findByPizzaImage(String pizzaImage);

    Optional<OrderDetail> findByPizzaName(String pizzaName);


}
