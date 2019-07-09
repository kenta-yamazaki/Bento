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
        Optional<BentoOrder> order = repository.findById(Integer.parseInt(id));
        if(!order.isPresent()){
            throw new NoSuchElementException("指定のIDはありません。");
        }else
        return order.get();
    }

    public void bentoInsert() {
        BentoOrder bentoOrder = new BentoOrder();
        Date date = Date.valueOf("2019-07-07");
        bentoOrder.setOrder_date(date);
        bentoOrder.setName("名無し");
        bentoOrder.setBento_id(1);
        bentoOrder.setRice_id(1);
        date.valueOf("2019-07-10");
        bentoOrder.setArrival(date);
        repository.save(bentoOrder);
    }
}
