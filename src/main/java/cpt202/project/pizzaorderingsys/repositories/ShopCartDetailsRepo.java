package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.ShoppingCartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopCartDetailsRepo extends JpaRepository<ShoppingCartDetails, Long> {

    Optional<ShoppingCartDetails> findShoppingCartDetailsByShoppingCartId(Long cartId);
    Optional<List<ShoppingCartDetails>> findShoppingCartDetailsAllByShoppingCartId(Long cartId);
}
