package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.service.BentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bento")
public class BentoController {

    private final BentoService bentoService;

    @RequestMapping("/all")
    public void selectAll() {
        bentoService.selectAll();
    }

    @RequestMapping("/select")
    public void select(@RequestParam("id") String id) {
        bentoService.selectData(id);
    }

    @PostMapping("/insert")
    public void insert() {
        bentoService.bentoInsert();
    }
}
