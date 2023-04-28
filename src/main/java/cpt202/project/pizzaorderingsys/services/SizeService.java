package cpt202.project.pizzaorderingsys.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cpt202.project.pizzaorderingsys.models.Size;
import cpt202.project.pizzaorderingsys.repositories.SizeRepo;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;

    private static Map<Integer, Size> sizes = null;

    static{
        sizes = new HashMap<Integer, Size>(); 
        sizes.put(1001, new Size(1001, "5", 0));
    }

    
    public Size newSize (Size size){
        return sizeRepo.save(size);
    }

    public List<Size> getSizeList(){
        return sizeRepo.findAll();
    }

    @Transactional
    public void deleteById(Integer id){
        sizeRepo.deleteById(id);
    }

    
    private static Integer initId = 1003;

    public void saveSize(Size size){
        if(size.getId() == null){
            size.setId(initId++);
        }
        sizes.put(size.getId(), size);
    }
    
    public Collection<Size> getAll(){
        return sizes.values();
    }

    public Size get(Integer id){
 
        return sizes.get(id);
    }
}


