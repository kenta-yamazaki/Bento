package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.Bento;
import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoOrderRepository;
import jp.co.esm.bento.application.repository.BentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class BentoService {
    private final BentoOrderRepository bentoOrderRepository;
    private final BentoRepository bentoRepository;

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

    public Bento selectBento(Integer id) throws NoSuchElementException {
        Optional<Bento> bento = bentoRepository.findById(id);
        if (!bento.isPresent()) {
            throw new NoSuchElementException("指定したIDはありません。");
        } else {
            return bento.get();
        }
    }

    public Map<String, String> getRadioItems() {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        List<Bento> bento = bentoRepository.findAll();
        for (int i = 0; i < bento.size(); i++) {
            selectMap.put(String.valueOf(bento.get(i).getId()), bento.get(i).getName());
        }
        return selectMap;
    }
}
