package jp.co.esm.bento.application.service;

import jp.co.esm.bento.application.BentoApplication;
import jp.co.esm.bento.application.entity.Bento;
import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.entity.Rice;
import jp.co.esm.bento.application.repository.BentoOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
    private BentoOrderRepository bentoOrderRepository;

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
        List<BentoOrder> before = bentoOrderRepository.findAll();

        LocalDate now = LocalDate.now();

        // 対象のメソッドを実行
        bentoService.create(new BentoOrder(
                null, "hoge", 1, 0, now.toString()
        ));

        // 実行後の状態を検証
        List<BentoOrder> after = bentoOrderRepository.findAll();
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
            , "INSERT INTO bento_order VALUES(1, 'hoge', 1, 0,'2017-07-22')", "INSERT INTO bento_order VALUES(2,'キティ',2,0,'2019-08-01')"})
    public void bentoOrderの全件渡すとBentoOrderRepositoryから全件返される() throws Exception {
        List<BentoOrder> bentoOrder = new ArrayList<>();
        bentoOrder.add(new BentoOrder(1, "hoge", 1, 0, "2017-07-22"));
        bentoOrder.add(new BentoOrder(2, "キティ", 2, 0, "2019-08-01"));
        assertEquals(bentoOrder, bentoService.selectAll());
    }

    @Test
    @Sql(statements = {"delete from bento;"
            , "INSERT INTO bento VALUES(0,'勇気',380)", "INSERT INTO bento VALUES(1,'本気',320)"})
    public void bentoの全件を渡すとBentoRepositoryから全件返される() throws Exception {
        List<Bento> bento = new ArrayList<>();
        bento.add(new Bento(0, "勇気", 380));
        bento.add(new Bento(1, "本気", 320));
        assertEquals(bento, bentoService.selectBentoAll());
    }

    @Test
    @Sql(statements = {"delete from rice;"
            , "INSERT INTO rice VALUES(0,'ご飯あり',110)", "INSERT INTO rice VALUES(1,'ご飯なし',0)"})
    public void riceの全件を渡すとRiceRepositoryから全件返される() throws Exception {
        List<Rice> rice = new ArrayList<>();
        rice.add(new Rice(0, "ご飯あり", 110));
        rice.add(new Rice(1, "ご飯なし", 0));
        assertEquals(rice, bentoService.selectRiceAll());
    }

    @Test
    @Sql(statements = {"delete from bento_order;"
            , "INSERT INTO bento_order VALUES(1, 'hoge', 1, 0,'2017-07-22')"})
    public void selectDataTest() throws Exception {
        BentoOrder bento = new BentoOrder(1, "hoge", 1, 0, "2017-07-22");
        assertEquals(bento, bentoService.selectData("1"));
    }

    @Test
    @Sql(statements = {"delete from bento_order;"
            , "INSERT INTO bento_order VALUES(1, 'hoge', 1, 0,'2017-07-22')"})
    public void selectDataErrTest() throws Exception {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            bentoService.selectData("0");
        });
        assertEquals("指定したIDはありません。", exception.getMessage());
    }

    @Test
    @Sql(statements = {"delete from bento;"
            , "INSERT INTO bento VALUES(0,'勇気',380)"})
    public void bentoのidを渡すとBentoRepositoryから1レコード返される() throws Exception {
        Bento bento = new Bento(0, "勇気", 380);
        assertEquals(bento, bentoService.selectBento(0));
    }

    @Test
    @Sql(statements = {"delete from bento;"
            , "INSERT INTO bento VALUES(0,'勇気',380)"})
    public void bentoのidを渡し該当するレコードがない場合エラーを返す() throws Exception {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            bentoService.selectBento(3);
        });
        assertEquals("指定したIDはありません。", exception.getMessage());
    }

    @Test
    @Sql(statements = {"delete from rice;"
            , "INSERT INTO rice VALUES(0,'ご飯あり',110)"})
    public void riceのidを渡すとRiceRepositoryから1レコード返される() throws Exception {
        Rice rice = new Rice(0, "ご飯あり", 110);
        assertEquals(rice, bentoService.selectRice(0));
    }

    @Test
    @Sql(statements = {"delete from rice;"
            , "INSERT INTO rice VALUES(0,'ご飯あり',110)"})
    public void riceのidを渡し該当するレコードがない場合エラーを返す() throws Exception {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            bentoService.selectRice(3);
        });
        assertEquals("指定したIDはありません。", exception.getMessage());
    }

    @Test
    @Sql(statements = {"delete from bento;"
            , "INSERT INTO bento VALUES(0,'勇気',380)", "INSERT INTO bento VALUES(1,'本気',320)"})
    public void getRadioBentoのテスト() throws Exception {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        selectMap.put("0", "勇気");
        selectMap.put("1", "本気");
        List<Bento> bento = bentoService.selectBentoAll();
        assertEquals(selectMap.get("0"), bento.get(0).getName());
        assertEquals(selectMap.get("1"), bento.get(1).getName());
    }

    @Test
    @Sql(statements = {"delete from rice;"
            , "INSERT INTO rice VALUES(0,'ご飯あり',110)", "INSERT INTO rice VALUES(1,'ご飯なし',0)"})
    public void getRadioRiceのテスト() throws Exception {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        selectMap.put("0", "ご飯あり");
        selectMap.put("1", "ご飯なし");
        List<Rice> rice = bentoService.selectRiceAll();
        assertEquals(selectMap.get("0"), rice.get(0).getAvailability());
        assertEquals(selectMap.get("1"), rice.get(1).getAvailability());
    }

}
