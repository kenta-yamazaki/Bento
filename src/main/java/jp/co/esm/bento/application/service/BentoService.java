package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.Test;
import jp.co.esm.bento.application.repository.BentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BentoService {

    private final BentoRepository repository;

    public List<Test> selectAll() {
        List<Test> list = repository.findAll();
        return list;
    }

    public Test selectData(String id) {
        Optional<Test> list = repository.findById(Integer.parseInt(id));
        return list.get();
    }

    public void bentoInsert() {
        Test test = new Test();
        test.setName("testName");
        test.setAge(34);
        repository.save(test);
    }
}
