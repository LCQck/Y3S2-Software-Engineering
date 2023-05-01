package cpt202.project.pizzaorderingsys.models;

import com.alibaba.fastjson2.annotation.JSONType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder.In;

@Entity
@JSONType(ignores = {"id","price"})
public class Taste {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String toppingName;
    private int toppingPrice;

    public Taste(){
    }

    public Taste(Integer id, String name, int price){
        this.id = id;
        this.toppingName = name;
        this.toppingPrice = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return toppingName;
    }

    public void setName(String name) {
        this.toppingName = name;
    }

    public int getPrice() {
        return toppingPrice;
    }

    public void setPrice(int price) {
        this.toppingPrice = price;
    }

}
