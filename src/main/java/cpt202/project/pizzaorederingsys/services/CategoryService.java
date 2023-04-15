package cpt202.project.pizzaorederingsys.services;

import cpt202.project.pizzaorederingsys.models.Category;
import cpt202.project.pizzaorederingsys.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
