package cpt202.project.pizzaorderingsys.services;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    public List<Order> chooseTime(String startDate, String endDate) throws ParseException {
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = st.parse(startDate);
        Date endTime = st.parse(endDate);

        List<Order> allOrder = orderRepo.findAll();
        List<Order> chosenOrder = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        try{
            for(Order order : allOrder){
                if(!StringUtils.isEmpty(startDate)&&!StringUtils.isEmpty(endDate)){

                    if(isEffectiveDate(order.getOrderTime(),startTime,endTime)){
                        chosenOrder.add(order);
                    }
                }else{
                    chosenOrder = allOrder;
                }


            }
        }catch (Exception e){
            System.out.println("not found");
        }

        chosenOrder.add(this.getOrderList(chosenOrder));
        return chosenOrder;
    }

}
