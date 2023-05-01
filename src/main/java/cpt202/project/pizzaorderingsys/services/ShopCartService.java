package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShoppingCart;
import cpt202.project.pizzaorderingsys.models.ShoppingCartDetails;
import cpt202.project.pizzaorderingsys.repositories.CustomerRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopCartService {

    @Autowired
    private ShopCartRepo shopCartRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public ShoppingCart newShopCart(ShoppingCart shopCart) {
        System.out.println("new shopcart with Id: "+shopCart.getId());
        return shopCartRepo.save(shopCart);
    }

    public ShoppingCart loadShopCartById(Long id) {
        if (!shopCartRepo.findById(id).isPresent()) {
            throw new NullPointerException("There is no existing shopcart");
        }
        return shopCartRepo.findById(id).get();
    }

    public ShoppingCart loadShopCartByCustomer(Customer customer) {
        if(!shopCartRepo.findShoppingCartByCustomerId(customer).isPresent()) {
            throw new NullPointerException("There is no existing shopcart");
        }
        return shopCartRepo.findShoppingCartByCustomerId(customer).get();
    }

    //    public void addShopCartDetailsByCustomer(Customer customer,
//                                                        ShoppingCartDetails shoppingCartDetails) {
//        ShoppingCart shoppingCart = loadShopCartByCustomer(customer);
//        shoppingCart.addShoppingCartDetail(shoppingCartDetails);
//
//    }
    @Transactional
    public void deleteShopCartByCustomer(Customer customer) {
        shopCartRepo.delete(loadShopCartByCustomer(customer));
    }
    @Transactional
    public void updateShopCartByCustomer( ShoppingCartDetails shoppingCartDetails,Customer customer) {
       /*ShoppingCart shoppingCart1 = loadShopCartByCustomer(customer);

        List<ShoppingCartDetails> detailList = shoppingCart1.getShoppingCartDetailsList();
        detailList.add(shoppingCartDetails);

        shoppingCart1.setCustomerId(customer);
        shoppingCart1.setShoppingCartDetailsList(detailList);
        shoppingCart1.setTotalPrice(shoppingCart.getTotalPrice()+shoppingCartDetails.getPrice());
        shoppingCart1.setId(customer.getId());

        System.out.println("delete shoppingCart: "+ shoppingCart1.getId());
        deleteShopCartByCustomer(customer);
        System.out.println("delete shoppingCart successfully");*/
        Double olderPrice = shoppingCartDetails.getShoppingCart().getTotalPrice();
        Double newPrice = olderPrice + shoppingCartDetails.getPrice();
        shopCartRepo.updateShoppingCartByCustomerId(newPrice,customer);

    }
    @Transactional
    public void setTotalPriceZero(ShoppingCart shoppingCart) {
        Customer customer = shoppingCart.getCustomerId();
        shopCartRepo.updateShoppingCartByCustomerId(0.0,customer);
    }


//    public void testAddShoppingCart(String username){
//        ShoppingCart shoppingCart = new ShoppingCart();
//        System.out.println("1");
//        System.out.println(username);
//        Customer customer = customerRepo.findCustomerByUserName(username).get();
////        System.out.println("customer1: "+customer1);
////        Customer customer2 = customerRepo.findCustomerByUserName((String)username).get();
////        System.out.println("customer2: "+customer2);
//        System.out.println("2");
//        shoppingCart.setCustomerId(customer);
//        System.out.println("3");
//        shoppingCart.setTotalPrice(0.0);
//        System.out.println("4");
//        shopCartRepo.save(shoppingCart);
//        System.out.println("5");
//    }


}
