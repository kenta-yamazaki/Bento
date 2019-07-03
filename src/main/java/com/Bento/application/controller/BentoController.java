package com.Bento.application.controller;

import java.util.List;
import java.util.Optional;

import com.Bento.application.entity.Counter;
import com.Bento.application.repository.BentoRepository;
import com.Bento.application.service.BentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/bento")
public class BentoController {

    @Autowired
    private BentoService bentoService;

    @Autowired
    private BentoRepository bentoRepository;

    @RequestMapping("/all")
    public List<Counter> selectAll() {
        List<Counter> list = bentoRepository.findAll();
        return list;
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
