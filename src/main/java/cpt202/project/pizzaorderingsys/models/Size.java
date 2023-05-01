package cpt202.project.pizzaorderingsys.models;



import com.alibaba.fastjson2.annotation.JSONType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JSONType(ignores = {"id","price"})
public class Size {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String inch;
    private int price;

    public Size(){
    }

    public Size(Integer id, String inch, int price){
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

    public String getInch() {
        return inch;
    }

    public void setInch(String inch) {
        this.inch = inch;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
