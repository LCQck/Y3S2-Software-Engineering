package cpt202.project.pizzaorderingsys.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cpt202.project.pizzaorderingsys.models.Taste;
import cpt202.project.pizzaorderingsys.repositories.TasteRepo;

@Service
public class TasteService {
    @Autowired
    private TasteRepo tasteRepo;

    private static Map<Integer, Taste> tastes = null;

    static{
        tastes = new HashMap<Integer, Taste>(); 
    }

    
    public Taste newTaste (Taste taste){
        return tasteRepo.save(taste);
    }

    public List<Taste> getTasteList(){
        return tasteRepo.findAll();
    }

    @Transactional
    public void deleteById(Integer id){
        tasteRepo.deleteById(id);
    }


    private static Integer initId = 1003;

    public void saveTaste(Taste taste){
        if(taste.getId() == null){
            taste.setId(initId++);
        }
        tastes.put(taste.getId(), taste);
    }
    
    public Collection<Taste> getAll(){
        return tastes.values();
    }

    public Taste get(Integer id){
 
        return tastes.get(id);
    }

    public Taste getByName(String name){
        return tasteRepo.findTasteByToppingName(name)
                .orElse(null);
    }
}
