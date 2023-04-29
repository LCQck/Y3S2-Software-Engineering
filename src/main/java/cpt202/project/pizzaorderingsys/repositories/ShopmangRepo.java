package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.ShopManager;
import cpt202.project.pizzaorderingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopmangRepo extends JpaRepository<ShopManager, Long> {

    Optional<ShopManager> findShopManagerByUserName(String username);

    @Modifying
    @Query("delete from ShopManager s where s.id = ?1")
    void deleteById(Long id);

    @Modifying
    @Query("delete from ShopManager s where s.userName = ?1")
    void deleteByUserName(String username);
}
