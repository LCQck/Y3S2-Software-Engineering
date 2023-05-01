package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    public Customer loadCustomerByUserName(String userName) {
        return customerRepo.findCustomerByUserName(userName)
                .orElseThrow(()-> new NullPointerException("There is no existing customer"));
    }
}
