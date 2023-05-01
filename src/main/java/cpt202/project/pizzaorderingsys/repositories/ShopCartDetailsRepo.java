package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.ShoppingCart;
import cpt202.project.pizzaorderingsys.models.ShoppingCartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopCartDetailsRepo extends JpaRepository<ShoppingCartDetails, Long> {

    Optional<ShoppingCartDetails> findShoppingCartDetailsByShoppingCartId(Long cartId);
    Optional<List<ShoppingCartDetails>> findShoppingCartDetailsAllByShoppingCartId(Long cartId);

    @Modifying
    @Query(value = "DELETE FROM shopping_cart_details WHERE shopping_cart_details.shopping_cart_detail_id = :id", nativeQuery=true)
    void deleteById(@Param("id") Long id);

    //    @Modifying
//    @Query(value = "DELETE * FROM shopping_cart_details WHERE shopping_cart_details.shopping_cart_id = :id", nativeQuery=true)
//    void deleteAllByDetailId(@Param("id") Long id);
    @Modifying
    @Query(value = "DELETE FROM shopping_cart_details WHERE shopping_cart_details.shopping_cart_id = :shoppingCart", nativeQuery=true)
    void deleteByShoppingCart(ShoppingCart shoppingCart);
}
