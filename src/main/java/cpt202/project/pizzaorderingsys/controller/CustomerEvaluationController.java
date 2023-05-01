package cpt202.project.pizzaorderingsys.controller;


import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pizzaOrderingSys/customer/evaluation")
public class CustomerEvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/list")
    public String getList(Model model, HttpServletRequest request){

        model.addAttribute("evaluationList",evaluationService.getOrderByCustomerName(request.getRemoteUser()));
        return "CustomerEvaluations";

    }

    @GetMapping("/add")
    public String addEvaluation(Long id, Model model){

        model.addAttribute("CustomerEvaluation", new Order());
        model.addAttribute("id",id);
        return "addCustomerComment";

    }

    @PostMapping("/add")
    public String confirmNewEvaluation(@ModelAttribute("CustomerEvaluation") Order order){

        evaluationService.updateCustomerOrder(order);
        return "redirect:/pizzaOrderingSys/customer/evaluation/list";

    }

    /*  @PostMapping("/delete")
    public String delete(@ModelAttribute("Evaluation") Evaluation evaluation) {
        evaluationService
        return "redirect:/evaluation/list";
    }
    */

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {

        evaluationService.deleteById(id);
        return "redirect:/pizzaOrderingSys/customer/evaluation/list";

    }

}
