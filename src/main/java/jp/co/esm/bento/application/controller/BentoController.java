package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;


@Controller
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/bento")
public class BentoController {
    private final BentoService bentoService;

    @RequestMapping("/all")
    public List<BentoOrder> selectAll() {
        return bentoService.selectAll();
    }



    @RequestMapping("/select")
    public Object selectData(@RequestParam("id") String id) {
        BentoOrder result = null;
        try {
            result = bentoService.selectData(id);
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
        return result;
    }

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
}
