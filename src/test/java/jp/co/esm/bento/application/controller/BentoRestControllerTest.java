package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.BentoApplication;
import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.service.BentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = BentoApplication.class)
class BentoRestControllerTest {
    private MockMvc mvc;

    @Mock
    private BentoService bentoService;

    @InjectMocks
    private BentoRestController bentoRestController;

    @BeforeEach
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(this.bentoRestController).build();
    }

    @Test
    public void allにGETリクエストすると200OKとbentoOrderのリストが返される() throws Exception {
        List<BentoOrder> bentoOrderList = new ArrayList<BentoOrder>();
        BentoOrder bentoOrder = new BentoOrder(1, "ミッキー", 1, 1, "2019-07-12");
        bentoOrderList.add(bentoOrder);

        when(bentoService.selectAll()).thenReturn(bentoOrderList);
        mvc.perform(get("/bento/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(bentoOrder.getId()))
                .andExpect(jsonPath("$[0].name").value(bentoOrder.getName()))
                .andExpect(jsonPath("$[0].bento_id").value(bentoOrder.getBento_id()))
                .andExpect(jsonPath("$[0].rice_id").value(bentoOrder.getRice_id()))
                .andExpect(jsonPath("$[0].arrival_date").value(bentoOrder.getArrival_date()));
        verify(bentoService).selectAll();
    }

    @Test
    public void selectにGETリクエストすると200OKとselectしたidのレコードが返される() throws Exception {
        BentoOrder bentoOrder = new BentoOrder(2, "ミニー", 2, 0, "2019-07-24");

        when(bentoService.selectData("2")).thenReturn(bentoOrder);
        mvc
                .perform(get("/bento/select?id=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(bentoOrder.getId()))
                .andExpect(jsonPath("$.name").value(bentoOrder.getName()))
                .andExpect(jsonPath("$.bento_id").value(bentoOrder.getBento_id()))
                .andExpect(jsonPath("$.rice_id").value(bentoOrder.getRice_id()))
                .andExpect(jsonPath("$.arrival_date").value(bentoOrder.getArrival_date()));
        verify(bentoService).selectData("2");
    }
}
