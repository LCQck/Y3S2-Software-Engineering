package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepo extends JpaRepository<Pizza, Long> {

    @Override
    Optional<Pizza> findById(Long aLong);

    Optional<Pizza> findPizzaByName(String name);
    List<Pizza> findAllByCategory(Category category);

//    Optional<Pizza> deletePizzaBy

    @Modifying
    @Query(value = "DELETE FROM pizza WHERE pizza_id = :id", nativeQuery=true)
    void deleteById(@Param("id") Integer id);


//    4.26 modification
    List<Pizza> findAll();

    @Modifying
//    @Query(value="select * from pizza where name like CONCAT('%',:name,'%')",nativeQuery=true)
    @Query(value="select * from pizza where pizza_name = :name",nativeQuery=true)
    List<Pizza> findByName(@Param("name") String name);

    Pizza findById(@Param("id") int id);

}
