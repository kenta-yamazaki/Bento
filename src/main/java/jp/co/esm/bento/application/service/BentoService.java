package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.entity.Rice;
import jp.co.esm.bento.application.repository.BentoRepository;
import jp.co.esm.bento.application.repository.RiceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class BentoService {
    private final BentoRepository bentoRepository;
    private final RiceRepository riceRepository;

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

    public Rice selectRice(Integer id) throws NoSuchElementException {
        Optional<Rice> rice = riceRepository.findById(id);
        if (!rice.isPresent()) {
            throw new NoSuchElementException("指定したIDはありません。");
        } else {
            return rice.get();
        }
    }

    public Map<String, String> getRadioRice() {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        List<Rice> rice = riceRepository.findAll();
        for (int i = 0; i < rice.size(); i++) {
            selectMap.put(String.valueOf(rice.get(i).getId()), rice.get(i).getAvailability());
        }
        return selectMap;
    }
}

