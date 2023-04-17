package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.models.Pizza;
import cpt202.project.pizzaorderingsys.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public void deleteById(Integer id){
        System.out.println("Enter delete service------");
        System.out.println(id);
        pizzaRepo.deleteById(id);
    }
}
