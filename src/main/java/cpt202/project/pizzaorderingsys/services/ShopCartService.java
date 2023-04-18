package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShoppingCart;
import cpt202.project.pizzaorderingsys.models.ShoppingCartDetails;
import cpt202.project.pizzaorderingsys.repositories.ShopCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCartService {

    @Autowired
    private ShopCartRepo shopCartRepo;

    public ShoppingCart newShopCart(ShoppingCart shopCart) {
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
    public void deleteShopCartByCustomer(Customer customer) {
        shopCartRepo.delete(loadShopCartByCustomer(customer));
    }

    public ShoppingCart updateShopCartByCustomer(Customer customer,
                                                        ShoppingCart shoppingCart,
                                                        ShoppingCartDetails shoppingCartDetails) {
        ShoppingCart shoppingCart1 = loadShopCartByCustomer(customer);
        shoppingCart1.setShoppingCartDetailsList(shoppingCart.getShoppingCartDetailsList());
        shoppingCart1.getShoppingCartDetailsList().add(shoppingCartDetails);
        shoppingCart1.setTotalPrice(shoppingCart.getTotalPrice()+shoppingCartDetails.getPrice());
        deleteShopCartByCustomer(customer);
        return newShopCart(shoppingCart1);
    }

}
