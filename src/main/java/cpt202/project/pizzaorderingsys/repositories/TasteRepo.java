package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Taste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TasteRepo extends JpaRepository<Taste, Long>{

    Optional<Taste> findTasteByToppingName(String toppingName);


//  4.26 modification
    @Modifying
    @Query(value="select * from taste where name like CONCAT('%',:name,'%')",nativeQuery=true)
    List<Taste> findByName(@Param("name") String name);

    List<Taste> findAll();

    @Modifying
    @Query(value = "DELETE FROM taste WHERE taste.id = :id", nativeQuery=true)
    void deleteById(@Param("id") Integer id);
}
