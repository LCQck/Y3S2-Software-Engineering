package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getOrderList(){
        return orderRepo.findAll();
    }

    public Order newOrder(Order order){
        return orderRepo.save(order);
    }

    public List<Order> getOrderByCustomerName(String customerName){
        return orderRepo.findByCustomerName(customerName)
                .orElse(null);
    }

    @Transactional
    public void deleteById (Long id){
        orderRepo.deleteById(id);
    }

    @Transactional
    public void updateOrder(Order order) {
        orderRepo.updateOrderComment(order.getComment(),order.getId());
    }

    @Transactional
    public void updateCustomerOrder(Order order) {
        orderRepo.updateCustomerOrderComment(order.getCustomerComment(), order.getId());
    }
}
