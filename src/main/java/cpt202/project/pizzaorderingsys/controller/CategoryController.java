package cpt202.project.pizzaorderingsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cpt202.project.pizzaorderingsys.services.CategoryService;
import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.repositories.CategoryRepo;

@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    // localhost:8080/category/list
    @GetMapping({"/list"})
    public String getList(Model model){
        model.addAttribute("categoryList", categoryService.getCategoryList());
        return "allCategory";
    }

    // localhost:8080/category/add
    @GetMapping("/add")
    public String addCategory(Model model){
        // System.out.println("222222");
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String confiromNewCategory(@ModelAttribute("category") Category category){
        System.out.println("222222");
        categoryService.newCategory(category);
        return "allPizza";
    }

    @GetMapping("/edit/{id}")
    public String toEditCategory(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "addCategory";
    }

    @PutMapping("/add")
    public String updateCategory(Category category){
        System.out.println(category);
        categoryService.newCategory(category);
        return "allPizza";
    }
    
//    @GetMapping("/delete/{id}")
//    public String toDeleteCategory(@PathVariable("id") Integer id, Model model){
//        return "deleteCategory";
//    }

    @RequestMapping("/delete/{id}")
    public String DeleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteById(id);
        return "allPizza";
    }
}
