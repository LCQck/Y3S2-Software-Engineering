package cpt202.project.pizzaorderingsys.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer inch;
    private int price;

    public Size(){
    }

    public Size(Integer id, Integer inch, int price){
        this.id = id;
        this.inch = inch;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInch() {
        return inch;
    }

    public void setInch(Integer inch) {
        this.inch = inch;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
