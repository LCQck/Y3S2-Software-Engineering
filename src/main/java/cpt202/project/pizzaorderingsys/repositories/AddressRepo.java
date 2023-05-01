package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.Address;
import cpt202.project.pizzaorderingsys.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AddressRepo extends JpaRepository<Address, Long> {
    List<Address> findAll();

    //List<Address> findAllByCustomer(Customer customer);

    Optional<List<Address>> findAllAddressByCustomer(Customer customer);

    @Modifying
    @Query(value = "DELETE FROM address WHERE address.address_id = :address_id", nativeQuery=true)
    void deleteById(@Param("address_id") Integer address_id);
}
