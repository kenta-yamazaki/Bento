package com.Bento.Bento.application.controller;

import java.util.ArrayList;
import java.util.List;

import com.Bento.Bento.application.repository.BentoRepository;
import com.Bento.Bento.application.counter.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BentoController {

    @Autowired
    BentoRepository repository;

    @RequestMapping("/select")
    public List<Counter> select(@RequestParam("id") int id) {
        List<Counter> list = repository.findById(id);
        return list;
    }

    @RequestMapping("/between")
    public List<Counter> between(@RequestParam("id1") String id1, @RequestParam("id2") String id2) {
        List<Counter> list = repository.findByIdBetween(id1, id2);
        return list;
    }

    @RequestMapping("/idin")
    public List<Counter> idIn(@RequestParam("id") String[] ids) {
        List<Counter> list = repository.findByIdIn(ids);
        return list;
    }

    @RequestMapping("/idnotin")
    public List<Counter> idNotIn(@RequestParam("id") ArrayList<String> ids) {
        List<Counter> list = repository.findByIdNotIn(ids);
        return list;
    }

}

