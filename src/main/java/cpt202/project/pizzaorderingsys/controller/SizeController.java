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
import cpt202.project.pizzaorderingsys.models.Size;
import cpt202.project.pizzaorderingsys.repositories.SizeRepo;
import cpt202.project.pizzaorderingsys.services.SizeService;

@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/size")
public class SizeController{

    @Autowired
    private SizeService sizeService;

    // localhost:8080/size/list
    @GetMapping({"/list"})
    public String getList(Model model){
        model.addAttribute("sizeList", sizeService.getSizeList());
        return "allSize";
    }

    // localhost:8080/size/add
    @GetMapping("/add")
    public String addSize(Model model){
        model.addAttribute("size", new Size());
        return "addSize";
    }

    @PostMapping("/add")
    public String confiromNewSize(@ModelAttribute("size") Size size){
        sizeService.newSize(size);
        return "allPizza";
    }

    @GetMapping("/edit/{id}")
    public String toEditSize(@PathVariable("id") Integer id, Model model){
        Size size = sizeService.get(id);
        model.addAttribute("size", size);
        return "addSize";
    }

    @PutMapping("/add")
    public String updateSize(Size size){
        System.out.println(size);
        sizeService.saveSize(size);
        return "allPizza";
    }
    
    // @GetMapping("/delete/{id}")
    // public String toDeleteSize(@PathVariable("id") Integer id, Model model){
    //     return "deleteSize";
    // }

    @RequestMapping("/delete/{id}")
    public String DeleteSize(@PathVariable("id") Integer id){
        sizeService.deleteById(id);
        return "allPizza";
    }


}
