package com.higor.timeline;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.higor.timeline.domain.CustomData;
import com.higor.timeline.domain.Evento;
import com.higor.timeline.services.EventoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimelineApplicationTests {
	
	@Autowired
	private EventoService service;
	
    @Autowired
    private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}
	
	@Test
    public void findAll() throws Exception {
		Evento eve1 = new Evento(1, "comprou", "2016-10-02T11:37:31.2300892-03:00", 120.0);
		CustomData cd1 = new CustomData(1, "transaction_id", "3409340");
		CustomData cd2 = new CustomData(2, "store_name", "BH Shopping");
		eve1.getCustom_data().addAll(Arrays.asList(cd1, cd2));

        List<Evento> events = Arrays.asList(eve1);
        given(service.findAll()).willReturn(events);
	
        this.mockMvc.perform(get("/"))
        	.andExpect(status().isOk())
        	.andExpect(content().json("[{'event': 'comprou', 'timestamp': '2016-10-02T11:37:31.2300892-03:00'}]"));
	}

	

}
