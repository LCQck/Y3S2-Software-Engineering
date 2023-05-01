package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.*;
import cpt202.project.pizzaorderingsys.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pizzaOrderingSys/customer")
public class ConfirmPageController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    AddressService addressService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    ShopCartDetailService shopCartDetailService;

    /////////////////////// this is for ShoppingCart Data to Order Data
    @GetMapping("/confirmationPage")
    public String gotoConfirmationPage(HttpServletRequest request) {
        String userName = request.getRemoteUser();
        System.out.println("the Username is " + userName);
//        User user = userService.loadUserByUserName(userName);

        return "ConfirmationPage";
    }
//

////////////////////////////////

    ///////////////////////////this is for Page Insert
    @PostMapping("/confirmationform")
    public String addInputInformation(@RequestParam String addressList,
                                    @RequestParam String remark,
                                    @RequestParam String payMethod,
                                    HttpServletRequest request) {
        System.out.println("----------Place Order----------");
        String userName = request.getRemoteUser();
        Customer customer = customerService.loadCustomerByUserName(userName);

        Long addressId = Long.valueOf(addressList);
        Address address = addressService.get(addressId);

        PayMethod payMethodOb = PayMethod.valueOf(payMethod);

        ShoppingCart shoppingCart = shopCartService.loadShopCartByCustomer(customer);
        Double totalPrice = shoppingCart.getTotalPrice();

        System.out.println("addressId: "+addressId);
        System.out.println("remark: "+remark);
        System.out.println(payMethodOb.toString());

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomer(customer);
        order.setOrderTime(new Date());
        order.setUpdateTime(new Date());
        order.setPayMethod(payMethodOb);
        order.setTotalPrice(totalPrice);
        order.setOrderRemark(remark);
        order.setCustomerName(address.getContactName());
        order.setCustomerPhone(address.getContactPhone());
        order.setCustomerAddress(address.getDetailAddress());
        Order newOrder = orderService.newOrder(order);
        List<ShoppingCartDetails> details = shopCartDetailService.loadShopCartDetailsByShopCartId(shoppingCart.getId());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (ShoppingCartDetails detail:
             details) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setPizzaName(detail.getPizzaName());
            orderDetail.setQuantity(detail.getCount());
            orderDetail.setPizzaPrice(detail.getPrice());
            orderDetail.setPizzaSize(detail.getSize().getInch());
            orderDetail.setPizzaTopping(detail.getTaste().getName());
            orderDetails.add(orderDetailService.newOrderDetail(orderDetail));
        }
        emptyShopCartDetails(shoppingCart);
        emptyShopCart(shoppingCart);
        return "redirect:/pizzaOrderingSys/customer/orderFinished";

    }

    @GetMapping("/orderFinished")
    public String orderFinished() {
        return "orderFinished";
    }

    public void emptyShopCartDetails(ShoppingCart shoppingCart) {
        System.out.println("---------emptyShopCartDetails----------");
        shopCartDetailService.emptyShopCart(shoppingCart);
    }

    public void emptyShopCart(ShoppingCart shoppingCart) {
        System.out.println("-----------emptyShopCart------------");
        shopCartService.setTotalPriceZero(shoppingCart);
    }


}



