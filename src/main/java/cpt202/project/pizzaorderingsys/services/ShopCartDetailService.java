package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.*;
import cpt202.project.pizzaorderingsys.repositories.PizzaRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopCartDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShopCartDetailService {

    @Autowired
    private PizzaRepo pizzaRepo;
    @Autowired
    private ShopCartDetailsRepo shopCartDetailsRepo;

    @Autowired
    private ShopCartService shopCartService;

    public ShoppingCartDetails newShopCartDetail(ShoppingCartDetails shopCartDetail) {
        return shopCartDetailsRepo.save(shopCartDetail);
    }

    public ShoppingCartDetails loadShopCartDetailById(Long id) {
        if (!shopCartDetailsRepo.findById(id).isPresent()) {
            throw new NullPointerException("There is no existing shop cart detail");
        }
        return shopCartDetailsRepo.findById(id).get();
    }

    public List<ShoppingCartDetails> loadShopCartDetailsByShopCartId(Long id) {
        if (!shopCartDetailsRepo.findShoppingCartDetailsAllByShoppingCartId(id).isPresent()) {
            throw new NullPointerException("There is no existing shop cart detail");
        }
        return shopCartDetailsRepo.findShoppingCartDetailsAllByShoppingCartId(id).get();
    }

    public boolean isShopCartDetailExist(Long id) {
        if(shopCartDetailsRepo.findShoppingCartDetailsAllByShoppingCartId(id).isPresent()){
            return true;
        }else {
            return false;
        }
    }

    public List<ShoppingCartDetails> getShoppingCartDetailsList(){
        return shopCartDetailsRepo.findAll();
    }


    public ShoppingCartDetails setShopCartDetail(Pizza pizza,
                                                 Size size,
                                                 Taste taste,
                                                 Integer amount,
                                                 ShoppingCart shoppingCart,
                                                 Customer customer){
        ShoppingCartDetails shoppingCartDetails = new ShoppingCartDetails();
        shoppingCartDetails.setPizzaName(pizza.getName());
        System.out.println("ShopCartDetailService  Pizza name :"+pizza.getName());
        System.out.println(pizza.getPrice());
        System.out.println(pizza.getDiscount());
        System.out.println(size.getPrice());
        System.out.println(taste.getPrice());
        System.out.println(amount);

        double price= (pizza.getPrice() + taste.getPrice())
                * (size.getPrice()*0.01 +1) * amount * (pizza.getDiscount()*0.01);
        price = (double) Math.round(price * 100) / 100;
        shoppingCartDetails.setPizzaName(pizza.getName());
        shoppingCartDetails.setPrice(price);
        shoppingCartDetails.setSize(size);
        shoppingCartDetails.setTaste(taste);
        shoppingCartDetails.setCount(amount);
        shoppingCartDetails.setShoppingCart(shoppingCart);

        shopCartService.updateShopCartByCustomer(shoppingCartDetails, customer);

        ShoppingCartDetails res = newShopCartDetail(shoppingCartDetails);
        System.out.println("ShopCartDetailService  save shop cart detail :");

        return res;
    }

    @Transactional
    public void deleteShopCartDetail(Long id) {
        shopCartDetailsRepo.deleteById(id);

    }

    @Transactional
    public void emptyShopCart(ShoppingCart shoppingCart) {
        shopCartDetailsRepo.deleteByShoppingCart(shoppingCart);
        shopCartService.setTotalPriceZero(shoppingCart);

    }

}
