package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class BentoService {

    @Autowired
    private BentoRepository bentoRepository;

    public List<BentoOrder> selectAll() {
        List<BentoOrder> list = bentoRepository.findAll();
        return list;
    }

    public void create(BentoOrder bentoOrder) {
        bentoRepository.save(bentoInsert(bentoOrder));
    }
    public BentoOrder bentoInsert(BentoOrder bentoOrder) {
        BentoOrder bentoOrder1 = new BentoOrder();
        bentoOrder1.setName(bentoOrder.getName());
        bentoOrder1.setBentoId(bentoOrder.getBentoId());
        bentoOrder1.setRiceId(bentoOrder.getRiceId());
        bentoOrder1.setArrivalDate(bentoOrder.getArrivalDate());
        return bentoOrder1;
    }

    public BentoOrder selectData(String id) throws NoSuchElementException {
        Optional<BentoOrder> order = bentoRepository.findById(Integer.parseInt(id));
        if (!order.isPresent()) {
            throw new NoSuchElementException("指定したIDはありません。");
        } else
            return order.get();
    }
}
