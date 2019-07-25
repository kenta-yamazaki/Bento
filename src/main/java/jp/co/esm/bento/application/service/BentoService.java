package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BentoService {
    private final BentoOrderRepository bentoOrderRepository;

    public List<BentoOrder> selectAll() {
        List<BentoOrder> list = bentoOrderRepository.findAll();
        return list;
    }

    public void create(BentoOrder bentoOrder) {
        bentoOrderRepository.save(bentoOrder);
    }

    public BentoOrder selectData(String id) throws NoSuchElementException {
        Optional<BentoOrder> order = bentoOrderRepository.findById(Integer.parseInt(id));
        if (!order.isPresent()) {
            throw new NoSuchElementException("指定したIDはありません。");
        } else {
            return order.get();
        }
    }
}
