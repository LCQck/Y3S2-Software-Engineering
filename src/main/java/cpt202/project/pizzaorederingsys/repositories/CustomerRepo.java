package cpt202.project.pizzaorederingsys.repositories;

import cpt202.project.pizzaorederingsys.models.Customer;
import cpt202.project.pizzaorederingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<User> findCustomerByUserName(String username);
}
