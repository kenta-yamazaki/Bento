package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bento")
public class BentoController {

    private final BentoService bentoService;

    @RequestMapping("/all")
    public List<BentoOrder> selectAll() {
        return bentoService.selectAll();
    }

    @RequestMapping("/select")
    public Object test(@RequestParam("id") String id) {
        BentoOrder result = null;
        try {
            result = bentoService.selectData(id);
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
        return result;
    }

    @PostMapping("/insert")
    public void insert() {
        bentoService.bentoInsert();
    }
}
