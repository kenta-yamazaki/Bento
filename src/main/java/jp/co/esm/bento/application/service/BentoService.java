package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BentoService {
    private final BentoRepository bentoRepository;

    public List<BentoOrder> selectAll() {
        List<BentoOrder> list = bentoRepository.findAll();
        return list;
    }

    public void create(BentoOrder bentoOrder) {
        bentoRepository.save(bentoOrder);
    }

    public BentoOrder selectData(String id) throws NoSuchElementException {
        Optional<BentoOrder> order = bentoRepository.findById(Integer.parseInt(id));
        if (!order.isPresent()) {
            throw new NoSuchElementException("指定したIDはありません。");
        } else {
            return order.get();
        }
    }
}

