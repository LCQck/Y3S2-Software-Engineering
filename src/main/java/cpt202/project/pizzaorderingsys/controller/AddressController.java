package cpt202.project.pizzaorderingsys.controller;

import com.alibaba.fastjson2.JSON;
import cpt202.project.pizzaorderingsys.models.Address;
import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.services.AddressService;
import cpt202.project.pizzaorderingsys.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pizzaOrderingSys/customer/addressBook")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @GetMapping({"/customerInfo"})
    public String getIndex(){
        return "CustomerInfo";
    }


    @GetMapping({"/list"})
    public String getList(Model model,HttpServletRequest request){
        String username = request.getRemoteUser();
        List<Address> addressList = addressService.loadAddressByCustomerId(
                customerService.loadCustomerByUserName(username).getId()
        );
        model.addAttribute("addressList", addressList);
        return "allAddress";
    }

    @GetMapping("/add")
    public String addAddress(Model model){
        System.out.println("Enter get address controller");
        model.addAttribute("address", new Address());
        return "addAddress";
    }

    @PostMapping("/add")
    public String confirmNewAddress(@RequestParam("address") String detailAddress,
                                    @RequestParam("contactName") String contactName,
                                    @RequestParam("contactPhone") String contactPhone,
                                    HttpServletRequest request){
        String customer = request.getRemoteUser();
        Customer customerOb = customerService.loadCustomerByUserName(customer);
        Address address = new Address();
        address.setCustomer(customerOb);
        address.setDetailAddress(detailAddress);
        address.setContactName(contactName);
        address.setContactPhone(contactPhone);
        addressService.newAddress(address);
        return "redirect:/pizzaOrderingSys/customer/addressBook/list";
    }

    @GetMapping("/edit/{address_id}")
    public String toEditAddress(@PathVariable("address_id") Long address_id, Model model){
        Address address = addressService.get(address_id);
        model.addAttribute("address", address);
        return "addAddress";
    }

    @PutMapping("/add")
    public String updateAddress(Address address){
        System.out.println(address);
        addressService.saveAddress(address);
        return "home";
    }
    
    @GetMapping("/delete/{address_id}")
    public String toDeleteAddress(@PathVariable("address_id") Integer address_id, Model model){
        System.out.println("11111111111111111111111111111");
        return "deleteAddress";
    }

    @PostMapping("/delete")
    public String DeleteAddress(@RequestParam("id") Integer address_id){
        System.out.println("Enter the delete process");
        addressService.deleteById(address_id);
        System.out.println("Delete successfully");
        return "redirect:/pizzaOrderingSys/customer/addressBook/list";
    }


    @PostMapping("/getCustomerAddress")
    public void getCustomerAddress(@RequestBody Map<String,Object> body,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        String username = request.getRemoteUser();
        Customer customerOb = customerService.loadCustomerByUserName(username);
        List<Address> addressList = addressService.loadAddressByCustomerId(customerOb.getId());
        String json = JSON.toJSONString(addressList);
        try {
            PrintWriter out = response.getWriter();
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // @RequestMapping(value="find")
    // public String find (Model model,HttpServletRequest request) {
    //     String name=request.getParameter("name");		//获取html页面搜索框的值
    //     List<Pizza> pizza=pizzaService.findByName(name);	//在数据库中进行查询
    //     model.addAttribute("pizza", pizzaService.findByName(name));	//模板映射
    //     return "searchResult";
    // }

}