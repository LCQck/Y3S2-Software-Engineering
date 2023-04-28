package cpt202.project.pizzaorderingsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.engine.AttributeName;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import cpt202.project.pizzaorderingsys.models.Taste;
import cpt202.project.pizzaorderingsys.services.TasteService;

@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/taste")
public class TasteController{

    @Autowired
    private TasteService tasteService;

    // localhost:8080/taste/list
    @GetMapping({"/list"})
    public String getList(Model model){
        Collection<Taste> taste = tasteService.getAll();
        model.addAttribute("tasteList", tasteService.getTasteList());
        return "allTaste";
    }

    // localhost:8080/taste/add
    @GetMapping("/add")
    public String addTaste(Model model){
        model.addAttribute("taste", new Taste());
        return "addTaste";
    }

    @PostMapping("/add")
    public String confiromNewTaste(@ModelAttribute("taste") Taste taste){
        tasteService.newTaste(taste);
        return "allPizza";
    }

    @GetMapping("/edit/{id}")
    public String toEditTaste(@PathVariable("id") Integer id, Model model){
        Taste taste = tasteService.get(id);
        model.addAttribute("taste", taste);
        return "addTaste";
    }

    @PutMapping("/add")
    public String updateTaste(Taste taste){
        System.out.println(taste);
        tasteService.saveTaste(taste);
        return "allPizza";
    }
    
    // @GetMapping("/delete/{id}")
    // public String toDeleteTaste(@PathVariable("id") Integer id, Model model){
    //     return "deleteTaste";
    // }

    @RequestMapping("/delete/{id}")
    public String DeleteTaste(@PathVariable("id") Integer id){
        tasteService.deleteById(id);
        return "allPizza";
    }


}