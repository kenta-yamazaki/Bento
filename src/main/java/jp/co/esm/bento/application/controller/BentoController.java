package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bento")
public class BentoController {
    private final BentoService bentoService;

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("bentoOrder", new BentoOrder());
        return "order";
    }

    @RequestMapping(value = "/user/orderHistory", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute BentoOrder bentoOrder, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order";
        } else {
            model.addAttribute("bentoOrder", bentoOrder);
            bentoService.create(bentoOrder);
            return "orderHistory";
        }
    }
    @RequestMapping(value = "/user/orderList", method = RequestMethod.GET)
    public String displayList(Model model){
        model.addAttribute("bentoOrderList", bentoService.selectAll());
        return "orderList";
    }
}
