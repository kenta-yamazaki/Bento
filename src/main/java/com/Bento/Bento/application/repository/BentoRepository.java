package com.Bento.Bento.application.repository;

import java.util.ArrayList;
import java.util.List;

import com.Bento.Bento.application.counter.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BentoRepository extends JpaRepository<Counter, String>{

    //List<Counter> findById(String id);
    List<Counter> findByIdBetween(String start, String end);
    List<Counter> findByIdIn(String[] ids);
    List<Counter> findByIdNotIn(ArrayList<String> ids);

}
