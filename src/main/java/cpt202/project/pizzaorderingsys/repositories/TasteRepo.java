package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Taste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasteRepo extends JpaRepository<Taste, Long>{

    Optional<Taste> findTasteByToppingName(String toppingName);
}
