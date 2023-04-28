package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoverService {
    @Autowired
    private OrderRepo orderRepo;
    public List<Order> getList(){
        return orderRepo.findAll();
    }

    public Order getOrderList(List<Order> allOrders){
        double sum = 0;
        for (Order order : allOrders) {
            sum += order.getTotalPrice();
        }
        return new Order(sum);

    }

}
