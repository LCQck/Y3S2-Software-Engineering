package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import cpt202.project.pizzaorderingsys.models.Pizza;
import cpt202.project.pizzaorderingsys.services.CategoryService;
import cpt202.project.pizzaorderingsys.services.OrderDetailService;
import cpt202.project.pizzaorderingsys.services.PizzaService;
import cpt202.project.pizzaorderingsys.services.ShopCartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import cpt202.project.pizzaorderingsys.PizzaOrderingSysApplication;
import cpt202.project.pizzaorderingsys.services.ShopCartService;

@Controller
@RequestMapping(path = "/pizzaOrderingSys/customer")
public class MenuController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShopCartDetailService shopcartDetailService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShopCartService shopCartService;

    //查看菜单里的所有Pizza和category
    @GetMapping(path = "/Menu")
    public String viewMenu() {
        return "MenuPage";
    }

    //查看某一类Pizza
    @PostMapping(path = "/viewByLabel")
    public void viewByLabel(@RequestBody Map<String, Object> body, HttpServletResponse response) {
        System.out.println("enter method ---------------");
        List<Pizza> pizzaList = pizzaService.getPizzaListByLabel(body.get("label").toString());
        System.out.println(pizzaList);
        String json = JSON.toJSONString(pizzaList);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.append(json);

        } catch (Exception e) {
            e.printStackTrace();
        }


//        return new Gson().toJson(pizzaList);
    }

    @PostMapping(path = "/viewById")
    public void viewById(@RequestBody int id, HttpServletResponse response) {
        System.out.println("enter method ---------------");
        Pizza pizza = pizzaService.findById(id);
        System.out.println(pizza);
        String json = JSON.toJSONString(pizza);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.append(json);

        } catch (Exception e) {
            e.printStackTrace();
        }


//        return new Gson().toJson(pizzaList);
    }

    //新建用户购物车
//    @PostMapping(path = "/createShoppingCart")
//    public void createShoppingCart(@RequestBody String username, HttpServletResponse response){
//        System.out.println("enter method ---------------");
//        System.out.println(username);
//        shopCartService.testAddShoppingCart(username);
//        System.out.println("success");
//        String json = JSON.toJSONString("success");
//        try {
//            PrintWriter out = response.getWriter();
//            response.setContentType("application/json");
//            out.append(json);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //查看用户信息
    @GetMapping( "/adressBook")
    public String customer() {
        return "adressBook";
    }



    //查看所有订单
    @GetMapping(path = "/order")
    public String showHistoryOrder(){
        return "Order";
    }

//    @GetMapping(path = "/test")
//    public String showPage(){
//        return "MenuPage";
//    }

}
