package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.RiceRepository;
import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bento")
public class BentoController {
    private final BentoService bentoService;
    private final RiceRepository riceRepository;

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("bentoOrder", new BentoOrder());
        model.addAttribute("radioRice", bentoService.getRadioRice());
        model.addAttribute("radioItems", bentoService.getRadioItems());
        return "order";
    }

    @RequestMapping(value = "/user/orderHistory", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute BentoOrder bentoOrder, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order";
        } else {
            model.addAttribute("bentoOrder", bentoOrder);
            model.addAttribute("riceAvailablity", bentoService.selectRice(bentoOrder.getRice_id()).getAvailability());
            model.addAttribute("bentoName", bentoService.selectBento(bentoOrder.getBento_id()).getName());

            bentoService.create(bentoOrder);
            return "orderHistory";
        }
    }

    @RequestMapping(value = "/user/orderList", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<String> bentoList = new ArrayList<>();
        for (int i = 0; i < bentoService.selectAll().size(); i++) {
            bentoList.add(bentoService.selectBento(bentoService.selectAll().get(i).getBento_id()).getName());
        }
        List<String> riceList = new ArrayList<>();
        for (int i = 0; i < bentoService.selectAll().size(); i++) {
            riceList.add(bentoService.selectRice(bentoService.selectAll().get(i).getRice_id()).getAvailability());
        }
        model.addAttribute("bentoOrderList", bentoService.selectAll());
        model.addAttribute("bentoNameList", bentoList);
        model.addAttribute("riceAvailablityList", riceList);

        return "orderList";
    }
}
