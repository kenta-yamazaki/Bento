package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.Bento;
import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.entity.Rice;
import jp.co.esm.bento.application.repository.RiceRepository;
import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        model.addAttribute("radioBento", bentoService.getRadioBento());
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

        List<BentoOrder> bentoOrderList = bentoService.selectAll();
        List<Bento> bentoList = bentoService.selectBentoAll();
        List<Rice> riceList = bentoService.selectRiceAll();

        Map<Integer,String> bentoMap = new HashMap<>();
        for(int i = 0;i < bentoList.size();i++){
            bentoMap.put(bentoList.get(i).getId(),bentoList.get(i).getName());
        }

        List<String> bentoNameList = bentoOrderList.stream().map(bentoOrder -> bentoMap.get(bentoOrder.getBento_id())
        ).collect(Collectors.toList());

        Map<Integer,String> riceMap = new HashMap<>();
        for(int i = 0;i < riceList.size();i++){
            riceMap.put(riceList.get(i).getId(),riceList.get(i).getAvailability());
        }

        List<String> riceAvailablityList = bentoOrderList.stream().map(bentoOrder -> riceMap.get(bentoOrder.getRice_id())
        ).collect(Collectors.toList());

        model.addAttribute("bentoOrderList", bentoOrderList);
        model.addAttribute("bentoNameList", bentoNameList);
        model.addAttribute("riceAvailablityList", riceAvailablityList);

        return "orderList";
    }
}
