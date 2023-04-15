package cpt202.project.pizzaorederingsys.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long id;
    @Column(name = "label",unique = true)
    private String label;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    //@JoinColumn(name = "pizza_id")
    private List<Pizza> pizzaInCategory;


    public Category() {
        this.pizzaInCategory = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addInCategory(Pizza pizza){
        this.pizzaInCategory.add(pizza);
    }

    public List getPizzaInCategory(){
        return pizzaInCategory;
    }

    public String printPizzaInCategory() {
        String res = this.label + ": [";
        for (Pizza item:this.pizzaInCategory
             ) {
            res+=item.getName();
            res+=", ";
        }
        res = res.substring(0, res.length()-2) + "]";
        return res;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Id
//    public Long getId() {
//        return id;
//    }

}
