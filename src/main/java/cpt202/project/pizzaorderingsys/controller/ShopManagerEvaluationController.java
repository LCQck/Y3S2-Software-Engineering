package cpt202.project.pizzaorderingsys.controller;

import cpt202.project.pizzaorderingsys.models.Order;
import cpt202.project.pizzaorderingsys.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pizzaOrderingSys/shopmanager/evaluation")
public class ShopManagerEvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/list")
    public String getList(Model model){
        model.addAttribute("evaluationList",evaluationService.getOrderList());
        return "allEvaluations";
    }

    @GetMapping("/add")
    public String addEvaluation(Long id, Model model){
        model.addAttribute("Evaluation", new Order());
        model.addAttribute("id",id);
        return "replyFeedback";
    }

    @PostMapping("/add")
    public String confirmNewEvaluation(@ModelAttribute("Evaluation") Order order){

        evaluationService.updateOrder(order);
        return "redirect:/shopmanager/evaluation/list";

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
        return "redirect:/shopmanager/evaluation/list";
    }
}
