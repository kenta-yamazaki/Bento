package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class BentoService {

    private final BentoRepository repository;

    public List<BentoOrder> selectAll() {
        List<BentoOrder> list = repository.findAll();
        return list;
    }

    public BentoOrder selectData(String id) throws NoSuchElementException {
        Optional<BentoOrder> list = repository.findById(Integer.parseInt(id));
        if(!list.isPresent()){
            throw new NoSuchElementException("指定のIDはありません。");
        }else
        return list.get();
    }

    public void bentoInsert() {
        BentoOrder bentoOrder = new BentoOrder();
        Date date = Date.valueOf("2019-07-07");
        bentoOrder.setOrder_date(date);
        bentoOrder.setOrder_name("名無し");
        bentoOrder.setOrder_menu(1);
        bentoOrder.setOrder_rice(1);
        date.valueOf("2019-07-10");
        bentoOrder.setOrder_arrival(date);
        repository.save(bentoOrder);
    }
}
