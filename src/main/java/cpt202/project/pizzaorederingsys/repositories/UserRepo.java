package cpt202.project.pizzaorederingsys.repositories;

import cpt202.project.pizzaorederingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String username);


}
