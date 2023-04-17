package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User newUser (User user){
        return userRepo.save(user);
    }

    public List<User> getTeamList(){
        return userRepo.findAll();
    }

}
