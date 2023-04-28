package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.models.Pizza;
import cpt202.project.pizzaorderingsys.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;
    @Autowired
    private CategoryService categoryService;


    public Pizza newPizza (Pizza pizza){
        return pizzaRepo.save(pizza);
    }
    public List<Pizza> getPizzaList(){
        return pizzaRepo.findAll();
    }

    public List<Pizza> getPizzaListByLabel(String label){
        Category category = categoryService.loadCategoryBylabel(label);
        return pizzaRepo.findAllByCategory(category);
    }

    public Pizza loadPizzaByName(String name){
        if (!pizzaRepo.findPizzaByName(name).isPresent()) {
            throw new NullPointerException("There is no existing pizza");
        }
        return pizzaRepo.findPizzaByName(name).get();
    }

    @Transactional
    public void deleteById(Integer id){
        System.out.println("Enter delete service------");
        System.out.println(id);
        pizzaRepo.deleteById(id);
    }


    //    4.26 modification
    private static Map<Integer, Pizza> pizzas = null;

    static{
        pizzas = new HashMap<Integer, Pizza>();
        pizzas.put(1001, new Pizza(1001, "pizza1", 1, 0, 1, "", ""));
    }

    public List<Pizza> findByName(String name){
        return pizzaRepo.findByName(name);
    }

    public Pizza findById(int id){
        return pizzaRepo.findById(id);
    }

    private static Integer initId = 1003;

    public void savePizza(Pizza pizza){
        if(pizza.getId() == null){
            pizza.setId(initId++);
        }
        pizzas.put(pizza.getId(), pizza);
    }

    public Collection<Pizza> getAll(){
        return pizzas.values();
    }

    public Pizza get(Integer id){
        return pizzas.get(id);
    }

}
