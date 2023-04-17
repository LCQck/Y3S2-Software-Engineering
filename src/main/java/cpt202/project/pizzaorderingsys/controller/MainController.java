package cpt202.project.pizzaorderingsys.controller;


import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShopManager;
import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(path = "/pizzaOrderingSys")
public class MainController {

    @Autowired
    private SecurityUserDetailsService userDetailsManager;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/index")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Enter index");
        System.out.println("auth: "+auth);
        System.out.println("auth.getAuthorities(): "+auth.getAuthorities().toString());
        if(auth.getAuthorities().toString()
                .replaceAll("\\[","").replaceAll("\\]","")
                .equals("ROLE_SHOP_MANAGER")){

            return "index";
        }
        else if(auth.getAuthorities().toString()
                .replaceAll("\\[","").replaceAll("\\]","")
                .equals("ROLE_CUSTOMER")){

            return "redirect:/pizzaOrderingSys/customer";
        }
        else {
            return "redirect:/pizzaOrderingSys/login";
        }
    }

    @GetMapping("/customer")
    public String showCustomer(){
        return "CustomerMainPage";
    }

//    @GetMapping("/login")
//    public String login(HttpServletRequest request, HttpSession session) {
//        System.out.println("HttpServletRequest request: "+request);
//        System.out.println("HttpSession session: "+ session);
//        session.setAttribute(
//                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
//        );
//        return "login";
//    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "loginPrototype";
    }

//    @PostMapping( value = "/login",
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
//            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
//    )
    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               //@RequestParam String vscode,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        System.out.println("processLogin: " +username);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("username", username);

               return "redirect:/pizzaOrderingSys/index";

        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            System.out.println("Error_processLogin: " +username);
            return "redirect:/pizzaOrderingSys/login";
        }
    }


    @GetMapping("/register")
    public String register() {
        return "RegisterAccount";
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public void addUser(@RequestParam Map<String, String> body, HttpServletRequest request) {
        String selectedOption = request.getParameter("radioOption");
        System.out.println(selectedOption);
        if(selectedOption.equals("1")){
            System.out.println("addcustomer");
            Customer customer = new Customer();
            customer.setCustomerUsername(body.get("username"));
            customer.setCustomerPassword(passwordEncoder.encode(body.get("password")));
            customer.setAccountNonLocked(false);
            userDetailsManager.createCustomer(customer);
        }
        else if(selectedOption.equals("2")){
            System.out.println("addshopmanager");
            ShopManager shopManager= new ShopManager();
            shopManager.setShopMangUsername(body.get("username"));
            shopManager.setShopMangPassword(passwordEncoder.encode(body.get("password")));
            shopManager.setAccountNonLocked(false);
            userDetailsManager.createShopManager(shopManager);
        }
        else {
            System.out.println("adduser");
            User user = new User();
            user.setUsername(body.get("username"));
            user.setPassword(passwordEncoder.encode(body.get("password")));
            user.setAccountNonLocked(false);
            userDetailsManager.createUser(user);
        }
    }
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "undefined_error";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        System.out.println("Method: getErrorMessage");
        System.out.println(error);
        return error;
    }
}

