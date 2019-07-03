package com.Bento.application.repository;

import com.Bento.application.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BentoRepository extends JpaRepository<Counter, Integer> {

}
