package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.models.Pizza;
import cpt202.project.pizzaorderingsys.services.CategoryService;
import cpt202.project.pizzaorderingsys.services.ImageService;
import cpt202.project.pizzaorderingsys.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @GetMapping({ "/list" })
    public String getList(Model model) {
        model.addAttribute("pizzaList", pizzaService.getPizzaList());
        return "allPizza";
    }

    @GetMapping("/add")
    public String addPizza(Model model) {
        System.out.println("Enter get pizza controller");
        model.addAttribute("pizza", new Pizza());
        return "addPizza";
    }

    @PostMapping("/add")
    public String confiromNewPizza(@RequestParam int id,
                                   @RequestParam String name,
                                   @RequestParam int price,
                                   @RequestParam int discount,
                                   @RequestParam int state,
                                   @RequestParam String category,
                                   @RequestParam String description,
                                   // @RequestParam Category category,
                                   @RequestParam("image") MultipartFile image) {
        // String path = pizza.getImage();
        // MultipartFile file = path;
        // int a = path.lastIndexOf("\\", 0);
        // System.out.println(path.substring(a));
        // String key = path.substring(a);
        // String url = imageService.imageUpload(path, key);
        // pizza.setImage(url);

        Category categoryOb = categoryService.loadCategoryBylabel(category);
        Pizza pizza = new Pizza();
        pizza.setId(id);
        pizza.setName(name);
        pizza.setPrice(price);
        pizza.setDiscount(discount);
        pizza.setState(state);
        pizza.setCategory(categoryOb);
        pizza.setDescription(description);
        // pizza.setCategoryName(category);
        try{
            String str = imageService.imageUpload(image);
            pizza.setImage(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException");
            e.printStackTrace();
        }
//        String str = imageService.imageUpload(image);

        pizzaService.newPizza(pizza);
        return "redirect:/pizzaOrderingSys/shopmanager/pizza/list";
    }

    @GetMapping("/edit/{id}")
    public String toEditPizza(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("pizza", pizza);
        return "addPizza";
    }

    @PutMapping("/add")
    public String updatePizza(Pizza pizza) {
        System.out.println(pizza);
        pizzaService.savePizza(pizza);
        return "redirect:/pizzaOrderingSys/shopmanager/pizza/list";
    }

    @RequestMapping("/delete/{id}")
    public String DeletePizza(@PathVariable("id") Integer id) {
        System.out.println("enter delete");
        pizzaService.deleteById(id);
        return "redirect:/pizzaOrderingSys/shopmanager/pizza/list";
    }

    @RequestMapping(value = "find")
    public String find(Model model, HttpServletRequest request) {
        String name = request.getParameter("name"); // 获取html页面搜索框的值
        List<Pizza> pizza = pizzaService.findByName(name); // 在数据库中进行查询
        model.addAttribute("pizza", pizzaService.findByName(name)); // 模板映射
        return "searchResult";
    }

    @RequestMapping("/detailpage/{id}")
    public String detailPage(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("pizza", pizza); // 模板映射
        return "detailPage";
    }

}
