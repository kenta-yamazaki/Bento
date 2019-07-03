package com.Bento.application.service;

import com.Bento.application.entity.Counter;
import com.Bento.application.repository.BentoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BentoService {

    @Autowired
    private BentoRepository repository;

    public Counter selectData(String id){
        Optional<Counter> list = repository.findById(Integer.parseInt(id));
        return list.get();
    }

    public void bentoInsert() {
        Counter counter = new Counter();
        counter.setName("testName");
        counter.setAge(34);
        repository.save(counter);
    }
}
