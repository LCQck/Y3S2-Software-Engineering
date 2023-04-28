package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long aLong);

    Optional<Category> findCategoryByLabel(String label);


//    4.26 modification
    @Modifying
    @Query(value = "DELETE FROM category WHERE label_id = :id", nativeQuery=true)
    void deleteById(@Param("id") Integer id);
}
