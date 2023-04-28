package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import cpt202.project.pizzaorderingsys.services.TurnoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/turnover")
public class TurnoverController {

    @Autowired
    private TurnoverService turnoverService;
    @Autowired
    private OrderRepo orderRepo;

//    public static class TimePeriod{
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        Date startTime;
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        Date endTime;
//        TimePeriod (){
//
//        }
//
//        public Date getEndTime() {
//            return endTime;
//        }
//
//        public Date getStartTime() {
//            return startTime;
//        }
//    }

    /**
     * 判断当前时间是否在时间范围内
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return boolean
     * @author zxzhang
     * @date 2020/3/16
     */
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

    @GetMapping("/list")
    public String getList(Model model) {
        //TimePeriod timePeriod = new TimePeriod();
        model.addAttribute("turnoverList", turnoverService.getList());
        return "allTurnover";
    }
    @PostMapping("/list")
    public String getTime(String startDate,String endDate, Model model) throws ParseException {
//        if(startTime.after(endTime)){
//            return "allTurnOver";
//        }
//        Date startTime = new Date(startDate);
//        Date endTime = new Date(endDate);*/
//        System.out.println(endDate);
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = st.parse(startDate);
        Date endTime = st.parse(endDate);

        List<Order> allOrder = orderRepo.findAll();
        List<Order> choosenOrder = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        try{
            for(Order order : allOrder){
                if(!StringUtils.isEmpty(startDate)&&!StringUtils.isEmpty(endDate)){

                    if(isEffectiveDate(order.getOrderTime(),startTime,endTime)){
                        choosenOrder.add(order);
                    }
                }else{
                    choosenOrder = allOrder;
                }


            }
        }catch (Exception e){
            System.out.println("not found");
        }

//        if(choosenOrder.isEmpty()){
//            allOrder.add(turnOverService.getOrderList(allOrder));
//            model.addAttribute("turnoverList",allOrder);
//            return "allTurnOver";
//        }

        choosenOrder.add(turnoverService.getOrderList(choosenOrder));

        model.addAttribute("turnoverList",choosenOrder);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        return "allTurnover";
    }

}
