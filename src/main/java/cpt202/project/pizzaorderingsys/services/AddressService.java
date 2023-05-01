package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Address;
import cpt202.project.pizzaorderingsys.repositories.AddressRepo;
import cpt202.project.pizzaorderingsys.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CustomerRepo customerRepo;

    private static Map<Long, Address> addresses = null;

//    static{
//        addresses = new HashMap<Long, Address>();
//    }


    public Address newAddress (Address address){
        return addressRepo.save(address);
    }

    public List<Address> getAddressList(){
        return addressRepo.findAll();
    }

    @Transactional
    public void deleteById(Integer address_id){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // addressRepo.deleteById(address_id);
        addressRepo.deleteById(address_id);
    }

    public List<Address> loadAddressByCustomerId(Long id){
        if (!addressRepo.findAllAddressByCustomer(customerRepo.findById(id).get()).isPresent()) {
            throw new NullPointerException("There is no existing address");
        }
        return addressRepo.findAllAddressByCustomer(customerRepo.findById(id).get()).get();
    }




    public void saveAddress(Address address){
        if(address.getAddress_id() == null){
        }
        addresses.put(address.getAddress_id(), address);
    }

    public Collection<Address> getAll(){
        return addresses.values();
    }

    public Address get(Long address_id){
        return addressRepo.findById(address_id).get();
    }


}
