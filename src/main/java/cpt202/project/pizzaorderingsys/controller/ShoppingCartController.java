package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import cpt202.project.pizzaorderingsys.models.*;
import cpt202.project.pizzaorderingsys.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/pizzaOrderingSys/customer/ShoppingCart")
public class ShoppingCartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private TasteService tasteService;

    @Autowired
    private SizeService sizeService;
    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private ShopCartDetailService shopcartDetailService;

    @GetMapping(path = "/list")
    public List<ShoppingCartDetails> getList() {
        return shopcartDetailService.getShoppingCartDetailsList();
    }


    @PostMapping("/addDetail")
    @ResponseBody
    public void addPizza(@RequestBody Map<String, Object> body,
                         HttpServletRequest request,
                         HttpServletResponse response
    ){
        System.out.println("Enter addDetail-------");
        String username = request.getRemoteUser();
        System.out.println("username: "+username);
        Customer belongCustomer = customerService.loadCustomerByUserName(username);
        System.out.println("belongCustomer: "+belongCustomer.getCustomerUsername());

        int pizzaId = Integer.parseInt(body.get("item_id").toString());
        Pizza pizza = pizzaService.findById(pizzaId);
        System.out.println("pizza: "+pizza.getName());

        String topping = body.get("item_topping").toString();
        Taste taste = tasteService.getByName(topping);
        System.out.println("taste: "+taste.getName());

        String sizeNum = body.get("item_size").toString();
        Size size = sizeService.getByInch(sizeNum);
        System.out.println("size: "+size.getInch());

        int amount = Integer.parseInt(body.get("item_amount").toString());
        System.out.println("amount: "+amount);

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(belongCustomer);
        System.out.println("shoppingCart: "+shoppingCart);

//        ShoppingCartDetails shoppingCartDetails = new ShoppingCartDetails();
//        shoppingCartDetails.setPizzaName(pizza.getName());
//        shoppingCartDetails.setCount(Integer.parseInt(body.get("item_amount").toString()));
//        shoppingCartDetails.setPrice(pizza.getPrice()*1.0);
//        shoppingCartDetails.setTaste();
//        shoppingCartDetails.setShoppingCart(shoppingCart);

        shopcartDetailService.setShopCartDetail(pizza, size,
                taste, amount, shoppingCart, belongCustomer);
//        shoppingCart.addShoppingCartDetail(shoppingCartDetails);
//        ShoppingCart shoppingCart1 = shopCartService.loadShopCartByCustomer(belongCustomer);
        List<ShoppingCartDetails> shoppingCartDetailsList
                = shopcartDetailService.loadShopCartDetailsByShopCartId(belongCustomer.getId());
        System.out.println("shoppingCartDetailsList: "+shoppingCartDetailsList);
        String resJson = JSON.toJSONString(shoppingCartDetailsList);
        System.out.println(resJson);
        try {
            PrintWriter out = response.getWriter();
            out.write(resJson);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping( "/viewCart")
    public void viewShoppingCart(HttpServletRequest request,
                                 HttpServletResponse response){
        System.out.println("Enter viewShoppingCart-------");
        String username = request.getRemoteUser();
        System.out.println("username: "+username);
        Customer belongCustomer = customerService.loadCustomerByUserName(username);
        System.out.println("belongCustomer: "+belongCustomer.getCustomerUsername());

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(belongCustomer);
        System.out.println("shoppingCart: "+shoppingCart);

        List<ShoppingCartDetails> shoppingCartDetailsList
                = shopcartDetailService.loadShopCartDetailsByShopCartId(belongCustomer.getId());
        System.out.println("shoppingCartDetailsList: "+shoppingCartDetailsList);
        String resJson = JSON.toJSONString(shoppingCartDetailsList);
        System.out.println(resJson);
        try {
            PrintWriter out = response.getWriter();
            out.write(resJson);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping( "/deleteItem")
    @ResponseBody
    public void deleteItem( @RequestBody Map<String, Object> body,
                            HttpServletRequest request,
                            HttpServletResponse response){
        System.out.println("Enter deleteItem-------");
        String username = request.getRemoteUser();
        System.out.println("username: "+username);
        Customer belongCustomer = customerService.loadCustomerByUserName(username);
        System.out.println("belongCustomer: "+belongCustomer.getCustomerUsername());

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(belongCustomer);
        System.out.println("shoppingCart: "+shoppingCart);

        shopcartDetailService.deleteShopCartDetail(Long.parseLong(body.get("cartDetailId").toString()));
        List<ShoppingCartDetails> shoppingCartDetailsList
                = shopcartDetailService.loadShopCartDetailsByShopCartId(belongCustomer.getId());
        System.out.println("shoppingCartDetailsList: "+shoppingCartDetailsList);
        String resJson = JSON.toJSONString(shoppingCartDetailsList);
        System.out.println(resJson);
        try {
            PrintWriter out = response.getWriter();
            out.write(resJson);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    //清空购物车
    @PostMapping(path = "/emptyCart")
    @ResponseBody
    public Map<String,Object> emptyCart(HttpServletRequest request,
                                        HttpServletResponse response){
        System.out.println("Enter emptyCart-------");
        String username = request.getRemoteUser();
        System.out.println("username: "+username);
        Customer belongCustomer = customerService.loadCustomerByUserName(username);
        System.out.println("belongCustomer: "+belongCustomer.getCustomerUsername());

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(belongCustomer);
        System.out.println("shoppingCart: "+shoppingCart);

        shopcartDetailService.emptyShopCart(shoppingCart);
        Map<String,Object> result = new HashMap<>();

        if(shopcartDetailService.isShopCartDetailExist(shoppingCart.getId()))
            result.put("success", true);
        else
            result.put("success", false);
        return result;
//        List<ShoppingCartDetails> shoppingCartDetailsList
//                = shopcartDetailService.loadShopCartDetailsByShopCartId(belongCustomer.getId());
//        System.out.println("shoppingCartDetailsList: "+shoppingCartDetailsList);
//        String resJson = JSON.toJSONString(shoppingCartDetailsList);
//        System.out.println(resJson);
//        try {
//            PrintWriter out = response.getWriter();
//            out.write(resJson);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

    //确认购物车
    @PostMapping(path = "/confirmCart")
    @ResponseBody
    public Map<String,Object> confirmCart(HttpServletRequest request,
                                          HttpServletResponse response) {
        System.out.println("Enter confirmCart-------");
        String username = request.getRemoteUser();
        System.out.println("username: " + username);
        Customer belongCustomer = customerService.loadCustomerByUserName(username);
        System.out.println("belongCustomer: " + belongCustomer.getCustomerUsername());

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(belongCustomer);
        System.out.println("shoppingCart: " + shoppingCart);

        shopcartDetailService.emptyShopCart(shoppingCart);
        Map<String, Object> result = new HashMap<>();

        if (shopcartDetailService.isShopCartDetailExist(shoppingCart.getId()))
            result.put("success", true);
        else
            result.put("success", false);
        return result;
    }


    //更新购物车

//    @PostMapping(path = "/update")
//    public ShoppingCart updateShoppingCart(@RequestBody ShoppingCart shoppingCart) {
//        ShoppingCart shoppingCart1 = shopCartService.loadShopCartByCustomer(shoppingCart.getCustomerId());
//        shopCartService.deleteShopCartByCustomer(shoppingCart1.getCustomerId());
//        ShoppingCart shoppingCart2 = new ShoppingCart();
//        shoppingCart2.setCustomerId(shoppingCart1.getCustomerId());
//        shoppingCart2.setTotalPrice(0.0);
//        shoppingCart2.setShoppingCartDetailsList(new ArrayList<>());
//        shopCartService.newShopCart(shoppingCart2);
//        return shoppingCart1;
//    }

    //返回到菜单页面
//   @GetMapping(path = "/menu")
//    public String back() {
//        return "redirect:/menu";
//    }


}
