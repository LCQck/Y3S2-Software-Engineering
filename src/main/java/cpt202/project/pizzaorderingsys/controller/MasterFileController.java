package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.User;
import cpt202.project.pizzaorderingsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/user")
public class MasterFileController {
    @Autowired
    private UserService userService;

    @GetMapping({"/list"})
    public String getList(Model model){
        model.addAttribute("userList", userService.getUserList());
        return "allUser";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add")
    public String confiromNewUser(@ModelAttribute("user") User user){
        userService.newUser(user);
        return "allUser";
    }

    @GetMapping("/edit/{id}")
    public String toEditUser(@PathVariable("id") Long id, Model model){
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "addUser";
    }

    @PutMapping("/add")
    public String updateUser(User user){
        System.out.println(user);
        userService.newUser(user);
        return "allUser";
    }

    @RequestMapping("/delete/{id}")
    public String DeleteUser(@PathVariable("username") String username){
        userService.deleteByUserName(username);
        return "allUser";
    }

}
