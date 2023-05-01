package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.repositories.OrderRepo;
import cpt202.project.pizzaorderingsys.services.EvaluationService;
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
@RequestMapping("/pizzaOrderingSys/shopmanager/turnover")
public class TurnoverController {

    @Autowired
    private TurnoverService turnoverService;

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

    @GetMapping("/list")
    public String getList(Model model) {

        //TimePeriod timePeriod = new TimePeriod();
        model.addAttribute("turnoverList", turnoverService.getList());
        return "allTurnover";

    }
    @PostMapping("/list")
    public String getTime(String startDate,String endDate, Model model) throws ParseException {

        model.addAttribute("turnoverList",turnoverService.chooseTime(startDate,endDate));
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        return "allTurnover";

    }

}
