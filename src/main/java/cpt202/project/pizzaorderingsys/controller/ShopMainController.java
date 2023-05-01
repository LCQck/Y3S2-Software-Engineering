package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderStatus;
import cpt202.project.pizzaorderingsys.services.OrderDetailService;
import cpt202.project.pizzaorderingsys.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson2.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@Controller

@RequestMapping("/pizzaOrderingSys/shopmanager")
public class ShopMainController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/mainpage")
    public String listOrders(Model model){
        System.out.println("Enter listOrders");
        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orderList", orderList);
        return "ShopManagerMainPage";
    }
    @GetMapping("/undeliveredorders")
    public String listUndeliveredOrders(Model model){
        System.out.println("Enter listUndeliveredOrders");
        List<Order> orderList = orderService.getOrderByOrderStatus(OrderStatus.PENDING);
        model.addAttribute("orderList", orderList);
        return "UndeliveredOrderPage";
    }

    @PostMapping("/listorders")
    public void listOrdersPost(HttpServletResponse response, Model model){
        System.out.println("Enter listOrdersPost");
        try {
            List<Order> orderList = orderService.getAllOrders();
            model.addAllAttributes(orderList);
//            String jsonStr = com.alibaba.fastjson2.JSON.toJSONString(orderList);
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.append(jsonStr);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
