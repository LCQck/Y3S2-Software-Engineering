package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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

    public User loadUserByUserName(String username){
        if (!userRepo.findUserByUserName(username).isPresent()) {
            throw new NullPointerException("There is no existing user");
        }
        return userRepo.findUserByUserName(username).get();
    }

    @Transactional
    public void deleteByUserName(String username) {userRepo.deleteByUserName(username);}

    public List<User> getUserList(){
        return userRepo.findAll();
    }
    private static Map<Integer, User> users = null;
    public User get(Long id){
        return users.get(id);
    }


}
