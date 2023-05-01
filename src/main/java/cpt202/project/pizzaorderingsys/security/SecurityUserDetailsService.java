package cpt202.project.pizzaorderingsys.security;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShopManager;
import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.repositories.CustomerRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopCartRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopmangRepo;
import cpt202.project.pizzaorderingsys.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ShopmangRepo shopRepository;
    @Autowired
    private CustomerRepo customerRepository;

    @Autowired
    private ShopCartRepo shopCartRepo;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not present"));
        return user;
    }
    public void createUser(UserDetails user) {
        userRepository.save((User) user);
        System.out.println("JPA_SaveUser");
    }
    public void createShopManager(UserDetails shopmang) {
        shopRepository.save((ShopManager) shopmang);
        System.out.println("JPA_SaveUser");
    }
    public void createCustomer(UserDetails customer) {
        customerRepository.save((Customer) customer);
        System.out.println("JPA_SaveUser");
    }

    public boolean isUserExists(String username) {
        if(userRepository.findUserByUserName(username).isPresent()) {
            System.out.println("JPA_UserExists");
            return true;
        }
        else{
            System.out.println("JPA_UserNotExists");
            return false;
        }


    }

    @Transactional
    public void deleteUser(String username){
        User user = userRepository.findUserByUserName(username).get();
        if (user.getAuthorities().toString()
                .replaceAll("\\[","").replaceAll("\\]","")
                .equals("ROLE_SHOP_MANAGER")){
            shopRepository.deleteById(user.getId());
        }
        else if(user.getAuthorities().toString()
                .replaceAll("\\[","").replaceAll("\\]","")
                .equals("ROLE_CUSTOMER")){
            customerRepository.deleteById(user.getId());
            shopCartRepo.deleteById(user.getId());
        }
        userRepository.deleteByUserName(username);
        System.out.println("JPA_DeleteUser");
    }







}
