package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopCartRepo extends JpaRepository<ShoppingCart, Long>{


    Optional<ShoppingCart> findShoppingCartByCustomerId(Long cartId);

    Optional<ShoppingCart> findShoppingCartByCustomerId(Customer customer);

//    Optional<ShoppingCart> updateShoppingCartByCustomerId(Customer customer, ShoppingCart shoppingCart);

    Optional<ShoppingCart> deleteShoppingCartByCustomerId(Customer customer);
}
