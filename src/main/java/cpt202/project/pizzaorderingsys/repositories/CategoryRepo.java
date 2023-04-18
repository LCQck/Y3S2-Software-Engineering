package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long aLong);

    Optional<Category> findCategoryByLabel(String label);
}
