package com.Bento.Bento.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Bento.Bento.application.counter.Counter;
import com.Bento.Bento.application.repository.BentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BentoController {

    @Autowired
    BentoRepository repository;

        @RequestMapping("/all")
    public List<Counter> selectAll() {
        List<Counter> list = repository.findAll();
        return list;
    }

        @RequestMapping("/select")
    public Counter select(@RequestParam("id") String id) {
        Optional<Counter> list = repository.findById(id);
        Counter result = list.get();
        return list.get();
    }

//    @RequestMapping("/between")
//    public List<Counter> between(@RequestParam("id1") String id1, @RequestParam("id2") String id2) {
//        List<Counter> list = repository.findByIdBetween(id1, id2);
//        return list;
//    }
//
//    @RequestMapping("S/idin")
//    public List<Counter> idIn(@RequestParam("id") String[] ids) {
//        List<Counter> list = repository.findByIdIn(ids);
//        return list;
//    }
//
//    @RequestMapping("/idnotin")
//    public List<Counter> idNotIn(@RequestParam("id") ArrayList<String> ids) {
//        List<Counter> list = repository.findAllById(ids);
//        return list;
//    }

}
