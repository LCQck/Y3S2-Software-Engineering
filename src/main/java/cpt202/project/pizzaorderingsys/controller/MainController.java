package cpt202.project.pizzaorderingsys.controller;


import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.models.ShopManager;
import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.security.SecurityUserDetailsService;
import cpt202.project.pizzaorderingsys.services.SendSms;
import cpt202.project.pizzaorderingsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/pizzaOrderingSys")
public class MainController {

    int verifyCode = 0;
    @Autowired
    private SecurityUserDetailsService userDetailsManager;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendSms sendSms;

    @GetMapping("/index")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Enter index");
        System.out.println("auth: "+auth);
        System.out.println("auth.getAuthorities(): "+auth.getAuthorities().toString());
        if(auth.getAuthorities().toString()
                .replaceAll("\\[","").replaceAll("\\]","")
                .equals("ROLE_SHOP_MANAGER")){

            return "redirect:/pizzaOrderingSys/shopmanager/mainpage";
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

    @RequestMapping( value ="/sendVerCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> processVerificationCode(@RequestBody Map<String, Object> body,
                                                       HttpSession session){
        System.out.println("processVerificationCode: " +body.get("UserPhoneNum").toString());
        //boolean userPhoneNumExists = userDetailsManager.isUserExists(body.get("userPhoneNum").toString());
        System.out.println("userPhoneNum " +body.get("UserPhoneNum").toString());
        //System.out.println("userPhoneNumExists: " +userPhoneNumExists);
         verifyCode = (int) ((Math.random() * 9 + 1) * 1000);
        System.out.println("verifyCode: " +verifyCode);
        Map<String, Object> response = new HashMap<>();
//        if (!userPhoneNumExists) {
//            response.put("success", true);
//            JsonObject json = JsonObject.fromObject(response);
        if(sendSms.sendVerificationCode(body.get("UserPhoneNum").toString())){
            response.put("success", true);
            response.put("vercode", verifyCode);
        }else {
            response.put("success", false);
        }
        return response;
    }



    @GetMapping("/register")
    public String register() {
        return "registerAccount";
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public String addUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("verification") String vercode,
                        HttpServletRequest request) {
        System.out.println("addUser: " +username);
        if(userDetailsManager.isUserExists(username)){
            System.out.println("User already exists");
            return "redirect:/pizzaOrderingSys/register";
        }
        String selectedOption = request.getParameter("radioOption");
        System.out.println(selectedOption);
        if((vercode.equals(String.valueOf(verifyCode))) && !vercode.isEmpty() ) {
            System.out.println("Need verifyCode: "+verifyCode);
            System.out.println("Input verifyCode:"+vercode);
            if (selectedOption.equals("1")) {
                System.out.println("addcustomer");
                Customer customer = new Customer();
                customer.setCustomerUsername(username);
                customer.setCustomerPassword(passwordEncoder.encode(password));
                customer.setAccountNonLocked(false);
                userDetailsManager.createCustomer(customer);
            } else if (selectedOption.equals("2")) {
                System.out.println("addshopmanager");
                ShopManager shopManager = new ShopManager();
                shopManager.setShopMangUsername(username);
                shopManager.setShopMangPassword(passwordEncoder.encode(password));
                shopManager.setAccountNonLocked(false);
                userDetailsManager.createShopManager(shopManager);
            } else {
                System.out.println("adduser");
                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setAccountNonLocked(false);
                userDetailsManager.createUser(user);
            }
            return "redirect:/pizzaOrderingSys/login";
        }
        else {
            System.out.println("Need verifyCode: "+verifyCode);
            System.out.println("Input verifyCode:"+vercode);
            return "redirect:/pizzaOrderingSys/register";
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

    @GetMapping("/forgetPassword")
    public String forgetPassword() {
        return "forgetPassword";
    }

    @PostMapping( value = "/forgetPassword",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public String forgetPassword(@RequestParam Map<String, String> body,  HttpSession session) {
        System.out.println("forget password: " + body.get("username"));
        System.out.println(body.get("username"));
        boolean userExists = userDetailsManager.isUserExists(body.get("username"));
        UserDetails oldUser = userDetailsManager.loadUserByUsername(body.get("username"));
        String author = oldUser.getAuthorities().toString()
                .replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(body.get("password"));
        System.out.println(body.get("confirmpassword"));
        if (body.get("verification").equals(String.valueOf(verifyCode)) && !body.get("verification").isEmpty() ) {
            System.out.println("Need verifyCode: "+verifyCode);
            System.out.println("Input verifyCode:"+body.get("verification"));
            if (userExists) {
                if (body.get("password").equals(body.get("confirmpassword"))) {
                    userDetailsManager.deleteUser(body.get("username"));
                    if (author.equals("ROLE_SHOP_MANAGER")) {
                        ShopManager shopManager = new ShopManager();
                        shopManager.setShopMangUsername(body.get("username"));
                        shopManager.setShopMangPassword(passwordEncoder.encode(body.get("password")));
                        shopManager.setAccountNonLocked(false);
                        userDetailsManager.createShopManager(shopManager);
                    } else if (author.equals("ROLE_CUSTOMER")) {
                        Customer customer = new Customer();
                        customer.setCustomerUsername(body.get("username"));
                        customer.setCustomerPassword(passwordEncoder.encode(body.get("password")));
                        customer.setAccountNonLocked(false);
                        userDetailsManager.createCustomer(customer);
                    } else {
                        User user = new User();
                        user.setUsername(body.get("username"));
                        user.setPassword(passwordEncoder.encode(body.get("password")));
                        user.setAccountNonLocked(false);
                        userDetailsManager.createUser(user);
                    }
                    return "redirect:/pizzaOrderingSys/login";
                } else{
                    System.out.println("Password not match");
                    return "redirect:/pizzaOrderingSys/forgetPassword";
                }
            } else {
                System.out.println("User not exists");
                return "redirect:/pizzaOrderingSys/forgetPassword";
            }
        }
        else{
            System.out.println("Need verifyCode: "+verifyCode);
            System.out.println("Input verifyCode:"+body.get("verification"));
            return "redirect:/pizzaOrderingSys/forgetPassword";
        }
    }

//    4.27 modification
    @GetMapping("/pizzaOrderingSys/shopmanager/pizza")
    public String pizzaHome(Model model) {
//        model.addAttribute("username", "Lily");
        return "pizzaHome";
    }

}

