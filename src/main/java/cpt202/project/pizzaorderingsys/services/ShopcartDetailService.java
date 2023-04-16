package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.*;
import cpt202.project.pizzaorderingsys.repositories.PizzaRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopCartDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopcartDetailService {

    @Autowired
    private PizzaRepo pizzaRepo;
    @Autowired
    private ShopCartDetailsRepo shopCartDetailsRepo;

    @Autowired
    private  ShopCartService shopCartService;

    public ShoppingCartDetails newShopcartDetail(ShoppingCartDetails shopcartDetail) {
        return shopCartDetailsRepo.save(shopcartDetail);
    }

    public ShoppingCartDetails loadShopcartDetailById(Long id) {
        if (!shopCartDetailsRepo.findById(id).isPresent()) {
            throw new NullPointerException("There is no existing shopcart detail");
        }
        return shopCartDetailsRepo.findById(id).get();
    }

    public void setShopcartDetail(Pizza pizza,
                                  Size size,
                                  Taste taste,
                                  Integer amount,
                                  ShoppingCart shoppingCart) {
        ShoppingCartDetails shoppingCartDetails = new ShoppingCartDetails();
        shoppingCartDetails.setPizzaName(pizza.getName());
        shoppingCartDetails.setPrice(
                (pizza.getPrice() + taste.getPrice())
                        * (size.getPrice()*0.01 +1) * amount * (pizza.getDiscount()*0.01)
                );
        shoppingCartDetails.setSize(size);
        shoppingCartDetails.setTaste(taste);
        shoppingCartDetails.setCount(amount);
        shoppingCartDetails.setShoppingCart(shoppingCart);
        newShopcartDetail(shoppingCartDetails);
        shopCartService.updateShopCartByCustomer(shoppingCart.getCustomerId(), shoppingCart, shoppingCartDetails);

    }

}
