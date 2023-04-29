package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByUserName(String username);
    @Modifying
    @Query("delete from Customer c where c.id = ?1")
    void deleteById(Long id);

    @Modifying
    @Query("delete from Customer c where c.userName = ?1")
    void deleteByUserName(String username);


}
