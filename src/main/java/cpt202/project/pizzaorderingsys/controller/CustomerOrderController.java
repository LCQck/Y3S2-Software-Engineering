package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderDetail;
import cpt202.project.pizzaorderingsys.models.OrderStatus;
import cpt202.project.pizzaorderingsys.services.OrderDetailService;
import cpt202.project.pizzaorderingsys.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Make a controller for orders and order details
 *
 **/

@Controller
@RequestMapping("/pizzaOrderingSys/customer")
public class CustomerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/orderhistory")
    public String listOrders(Model model, HttpServletRequest request){
        System.out.println("Enter listOrders");
        List<Order> orderList = orderService.getOrderByCustomerName(request.getRemoteUser());
        model.addAttribute("orderList", orderList);
        return "OrderHistoryPage";
    }

    @PostMapping("/listorders")
    public void listOrdersPost(HttpServletResponse response, Model model){
        System.out.println("Enter listOrdersPost");
        try {
            List<Order> orderList = orderService.getOrderByCustomerName("customer");
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

    @PostMapping("/getOrderDetails")
    public void getOrderDetails(@RequestBody Map<String, Object> map, HttpServletResponse response) {
        System.out.println("Enter getOrderDetails");
        System.out.println(map.toString());
        int a =(int) map.get("orderId");
        Long Id = (long) a;

        System.out.println(Id);

        Order order = orderService.getOrderById(Id).get();
        List<OrderDetail> detailList = orderDetailService.getOrderDetailByOrder(order);
        System.out.println(detailList.toString());
        try{
            String jsonStr = JSON.toJSONString(detailList);
            System.out.println(jsonStr);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.append(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@PostMapping("/updateorderstatus/{orderId}/{orderStatus}")
    public void updateOrderAjax(@PathVariable Long orderId, @PathVariable String orderStatus) {

        orderService.updateOrderStatus(orderId, orderStatus);
        if (orderService.getOrderByOrderId(orderId).get().getOrderStatus().equals(orderStatus))
            System.out.println("Order status updated successfully");
        else
            System.out.println("Order status updated failed");
    }

     */

    //Add a controller function that allows the customer to cancel the order only under PENDING status
    @RequestMapping("/updateOrder/{orderId}/{orderStatus}")
    public void customerCancelOrder(@PathVariable String orderStatus, @PathVariable Long Id) {

        OrderStatus status = OrderStatus.valueOf(orderStatus);
        Order order = orderService.getOrderById(Id).get();
        if(order.getOrderStatus().equals(OrderStatus.PENDING)) {
            orderService.updateOrderStatus(Id,status);

            if (orderService.getOrderById(Id).get().getOrderStatus().equals(status))
                System.out.println("Order status updated successfully");
            else
                System.out.println("Order status updated failed");
        }
        else
            System.out.println("Order status is not PENDING, cannot cancel");
    }

    @RequestMapping (value = "/updateorderstatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCustomerOrderAjax(
            @RequestBody Map<String, Object> map
 //           HttpServletRequest request
    ) {
        System.out.println("Enter updateCustomerOrderAjax");
        int a =(int) map.get("orderId");
        Long Id = (long) a;
        Map<String, Object> response = new HashMap<>();
        String orderStatus = (String) map.get("orderStatus");
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        if(orderService.getOrderById(Id).get().getOrderStatus().equals(OrderStatus.PENDING)) {
            orderService.updateOrderStatus(Id,status);
            if (orderService.getOrderById(Id).get().getOrderStatus().equals(status)) {
                System.out.println("Order status updated successfully");
                response.put("success", true);
            } else {
                System.out.println("Order status updated failed");
                response.put("success", false);
            }
            return response;
        }
        else {
            System.out.println("Order status is not PENDING, cannot cancel");
            response.put("success", false);
            return response;
        }
    }

}
