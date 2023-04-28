package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SizeRepo extends JpaRepository<Size, Long> {

//    Optional<Size> findSizeByInch(int size);


//    4.26 modification
    @Modifying
    @Query(value="select * from size where inch like CONCAT('%',:size,'%')",nativeQuery=true)
    List<Size> findByName(@Param("size") String size);

    List<Size> findAll();

    @Modifying
    @Query(value = "DELETE FROM size WHERE size.id = :id", nativeQuery=true)
    void deleteById(@Param("id") Integer id);
}
