package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.BentoApplication;
import jp.co.esm.bento.application.controller.BentoController;
import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.repository.BentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = BentoApplication.class)
@Transactional
class BentoServiceTest {
    private MockMvc mvc;

    @Autowired
    private BentoService bentoService;

    @Autowired
    private BentoRepository bentoRepository;

    @Test
    @Sql(statements = {"delete from bento_order;"
            , "insert into bento_order values(2,'ドナルド',1,1,'2019-07-19'),(3,'デイジー',2,0,'2019-07-27');"})
    public void selectAllTest() {
        List<BentoOrder> memberList = new ArrayList<BentoOrder>();
        BentoOrder bentoOrder = new BentoOrder(2, "ドナルド", 1, 1, "2019-07-19");
        BentoOrder bentoOrder2 = new BentoOrder(3, "デイジー", 2, 0, "2019-07-27");
        memberList.add(bentoOrder);
        memberList.add(bentoOrder2);

        assertEquals(memberList, bentoService.selectAll());
    }

    @Test
    @Sql(statements = {"delete from bento_order;"})
    public void createTest() {

        // 実行前の状態を確認
        List<BentoOrder> before = bentoRepository.findAll();

        LocalDate now = LocalDate.now();

        // 対象のメソッドを実行
        bentoService.create(new BentoOrder(
                null, "hoge", 1, 0, now.toString()
        ));

        // 実行後の状態を検証
        List<BentoOrder> after = bentoRepository.findAll();
        assertEquals(before.size() + 1, after.size());
        assertAll(
                () -> assertEquals("hoge", after.get(0).getName()),
                () -> assertEquals((Integer) 1, after.get(0).getBento_id()),
                () -> assertEquals((Integer) 0, after.get(0).getRice_id()),
                () -> assertEquals(now.toString(), after.get(0).getArrival_date())
        );
    }

    @Test
    @Sql(statements = {"delete from bento_order;"
            , "INSERT INTO bento_order VALUES(1, 'hoge', 1, 0,'2017-07-22')"})
    public void selectDataTest() {
        BentoOrder bento = new BentoOrder(1, "hoge", 1, 0, "2017-07-22");
        assertEquals(bento, bentoService.selectData("1"));
    }

    @Test
    @Sql(statements = {"delete from bento_order;"
            , "INSERT INTO bento_order VALUES(1, 'hoge', 1, 0,'2017-07-22')"})
    public void selectDataErrTest() {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            bentoService.selectData("0");
        });
        assertEquals("指定したIDはありません。", exception.getMessage());
    }
}
