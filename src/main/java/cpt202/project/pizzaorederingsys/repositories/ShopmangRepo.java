package cpt202.project.pizzaorederingsys.repositories;

import cpt202.project.pizzaorederingsys.models.ShopManager;
import cpt202.project.pizzaorederingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopmangRepo extends JpaRepository<ShopManager, Long> {

    Optional<User> findShopManagerByUserName(String username);
}
