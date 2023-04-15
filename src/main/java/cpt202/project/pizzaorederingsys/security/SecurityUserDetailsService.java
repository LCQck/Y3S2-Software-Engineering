package cpt202.project.pizzaorederingsys.security;

import cpt202.project.pizzaorederingsys.models.Customer;
import cpt202.project.pizzaorederingsys.models.ShopManager;
import cpt202.project.pizzaorederingsys.models.User;
import cpt202.project.pizzaorederingsys.repositories.CustomerRepo;
import cpt202.project.pizzaorederingsys.repositories.ShopmangRepo;
import cpt202.project.pizzaorederingsys.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ShopmangRepo shopRepository;
    @Autowired
    private CustomerRepo customerRepository;


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
}
