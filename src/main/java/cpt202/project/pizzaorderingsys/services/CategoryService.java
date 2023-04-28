package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Category;
import cpt202.project.pizzaorderingsys.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category newCategory (Category category){

        if (categoryRepo.findCategoryByLabel(category.getLabel()).isPresent()) {
            throw new IllegalArgumentException("The category is existing");
        }
        return categoryRepo.save(category);
    }
    public List<Category> getCategoryList(){
        return categoryRepo.findAll();
    }

    public Category loadCategoryBylabel(String label){

        if (!categoryRepo.findCategoryByLabel(label).isPresent()) {
            throw new NullPointerException("There is no existing label");
        }
        return categoryRepo.findCategoryByLabel(label).get();
    }

//    4.27 Modification
    private static Map<Integer, Category> categories = null;
    static{
        categories = new HashMap<Integer, Category>();
        // categories.put(1001, new Category(, " "));
    }

    public Category get(Integer id){
        return categories.get(id);
    }

    @Transactional
    public void deleteById(Integer id){
        categoryRepo.deleteById(id);
    }
}
