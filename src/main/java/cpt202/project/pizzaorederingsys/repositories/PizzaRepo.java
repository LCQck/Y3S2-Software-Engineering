package cpt202.project.pizzaorederingsys.repositories;

import cpt202.project.pizzaorederingsys.models.Category;
import cpt202.project.pizzaorederingsys.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepo extends JpaRepository<Pizza, Long> {

    @Override
    Optional<Pizza> findById(Long aLong);

    Optional<Pizza> findPizzaByName(String pizzaName);
    List<Pizza> findAllByCategory(Category category);

//    Optional<Pizza> deletePizzaBy

    @Modifying
    @Query(value = "DELETE FROM pizza WHERE pizza.id = :id", nativeQuery=true)
    void deleteById(@Param("id") Integer id);

}
