package jp.co.esm.bento.application.controller;

import jp.co.esm.bento.application.entity.BentoOrder;
import jp.co.esm.bento.application.service.BentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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
class BentoControllerTest {
    private MockMvc mvc;

    @Mock
    private BentoService bentoService;

    @InjectMocks
    private BentoController bentoController;

    @BeforeEach
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(this.bentoController).build();
    }


    @Disabled("テストしない")
    @Test
    public void bentoOrderにGETリクエストすると200OKとMemberのリストが返される() throws Exception {
        List<BentoOrder> memberList = new ArrayList<BentoOrder>();
        BentoOrder bentoOrder = new BentoOrder(1, "ミッキー", 1, 1, "2019-07-12");
        memberList.add(bentoOrder);

        when(bentoService.selectAll()).thenReturn(memberList);
        mvc.perform(get("/bento/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(bentoOrder.getId()))
                .andExpect(jsonPath("$[0].name").value(bentoOrder.getName()))
                .andExpect(jsonPath("$[0].bento_id").value(bentoOrder.getBento_id()))
                .andExpect(jsonPath("$[0].rice_id").value(bentoOrder.getRice_id()))
                .andExpect(jsonPath("$[0].arrival_date").value(bentoOrder.getArrival_date()));
        verify(bentoService).selectAll();
    }

}
