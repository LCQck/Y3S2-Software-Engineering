package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopCartRepo extends JpaRepository<ShoppingCart, Long>{


    Optional<ShoppingCart> findShoppingCartByCustomerId(Long cartId);

    Optional<ShoppingCart> findShoppingCartByCustomerId(Customer customer);

    @Modifying
    @Query("update ShoppingCart s set s.totalPrice = ?1 where s.customerId = ?2")
    void updateShoppingCartByCustomerId(Double price, Customer customer);

    @Modifying
    @Query("delete from ShoppingCart s where s.customerId = ?1")
    void deleteShoppingCartByCustomerId(Customer customer);
    @Modifying
    @Query("delete from ShoppingCart s where s.id = :id")
    void deleteById (Long id);
}
