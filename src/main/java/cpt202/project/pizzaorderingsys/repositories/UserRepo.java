package cpt202.project.pizzaorderingsys.repositories;

import cpt202.project.pizzaorderingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String username);

//    Optional<User> existsByUserName(String username);

    @Modifying
    @Query(value = "DELETE FROM user WHERE user.username = :username", nativeQuery=true)
    void deleteByUserName(@Param("username") String username);
}
