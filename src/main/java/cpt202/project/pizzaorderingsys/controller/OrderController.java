package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.models.OrderDetail;
import cpt202.project.pizzaorderingsys.models.OrderStatus;
import cpt202.project.pizzaorderingsys.services.OrderDetailService;
import cpt202.project.pizzaorderingsys.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pizzaOrderingSys/shopmanager/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/updateOrder/{orderId}/{orderStatus}")
    public void updateOrder(@PathVariable Long orderId, @PathVariable String orderStatus) {

        OrderStatus status = OrderStatus.valueOf(orderStatus);
        orderService.updateOrderStatus(orderId, status);

        if(orderService.getOrderByOrderId(orderId).get().getOrderStatus().equals(status))
            System.out.println("Order status updated successfully");
        else
            System.out.println("Order status updated failed");

    }

    @RequestMapping (value = "/updateorderstatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateOrderAjax(
           @RequestBody Map<String, Object> map
//            HttpServletRequest request
    ) {
        System.out.println("Enter updateOrderAjax");
        int a =(int) map.get("orderId");
        Long orderId = (long) a;
        Map<String, Object> response = new HashMap<>();
        String orderStatus = (String) map.get("orderStatus");
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        orderService.updateOrderStatus(orderId, status);
        if (orderService.getOrderByOrderId(orderId).get().getOrderStatus().equals(status)) {
            System.out.println("Order status updated successfully");
            response.put("success", true);
        }
        else {
            System.out.println("Order status updated failed");
            response.put("success", false);
        }
        return response;
    }

    @PostMapping("/getOrderDetails")
    public void getOrderDetails(@RequestBody Map<String, Object> map, HttpServletResponse response) {
        System.out.println("Enter getOrderDetails");
        System.out.println(map.toString());
        int a =(int) map.get("orderId");
        Long orderId = (long) a;

        System.out.println(orderId);

        Order order = orderService.getOrderByOrderId(orderId).get();
        List<OrderDetail> detailList = orderDetailService.getOrderDetailByOrder(order);
        System.out.println(detailList.toString());
        try{
            String jsonStr = com.alibaba.fastjson2.JSON.toJSONString(detailList);
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
}
