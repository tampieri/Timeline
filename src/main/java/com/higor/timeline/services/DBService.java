package com.higor.timeline.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higor.timeline.domain.CustomData;
import com.higor.timeline.domain.Evento;
import com.higor.timeline.repositories.CustomDataRepository;
import com.higor.timeline.repositories.EventoRepository;

@Service
public class DBService {
	
	@Autowired
	private CustomDataRepository customDataRepository;
	
	@Autowired
	private EventoRepository eventoRepository;

	public void instantiateTesDataBase() throws ParseException {
		
		Evento eve1 = new Evento(1, "comprou", "2016-10-02T11:37:31.2300892-03:00", 120.0);
		CustomData cd1 = new CustomData(1, "transaction_id", "3409340");
		CustomData cd2 = new CustomData(2, "store_name", "BH Shopping");
		
		eve1.getCustom_data().addAll(Arrays.asList(cd1, cd2));
		
		Evento eve2 = new Evento(2, "comprou-produto", "2016-10-02T11:37:31.2300892-03:00", 120.0);
		CustomData cd3 = new CustomData(3, "transaction_id", "3409340");
		CustomData cd4 = new CustomData(4, "product_name", "Tenis Preto");
		CustomData cd5 = new CustomData(5, "product_price", "120");
		eve2.getCustom_data().addAll(Arrays.asList(cd3, cd4, cd5));
		
		Evento eve3 = new Evento(3, "comprou", "2016-09-02T11:37:31.2300892-03:00", 250.0);
		CustomData cd6 = new CustomData(6, "transaction_id", "3029384");
		CustomData cd7 = new CustomData(7, "store_name", "Patio Savassi");
		eve3.getCustom_data().addAll(Arrays.asList(cd6, cd7));
		
		Evento eve4 = new Evento(4, "comprou-produto", "2016-09-02T11:37:31.2300892-03:00", 250.0);
		CustomData cd8 = new CustomData(8, "transaction_id", "3029384");
		CustomData cd9 = new CustomData(9, "product_name", "Camisa Azul");
		CustomData cd10 = new CustomData(10, "product_price", "100");
		eve4.getCustom_data().addAll(Arrays.asList(cd8, cd9, cd10));
		
		Evento eve5 = new Evento(5, "comprou-produto", "2016-09-02T11:37:31.2300892-03:00", 250.0);
		CustomData cd11 = new CustomData(11, "transaction_id", "3029384");
		CustomData cd12 = new CustomData(12, "product_name", "Calça Rosa");
		CustomData cd13 = new CustomData(13, "product_price", "150");
		eve5.getCustom_data().addAll(Arrays.asList(cd11, cd12, cd13));
		
		customDataRepository.saveAll(Arrays.asList(cd1,cd2,cd3,cd4,cd5,cd6,cd7,cd8,cd9,cd10,cd11,cd12,cd13));
		eventoRepository.saveAll(Arrays.asList(eve1, eve2, eve3, eve4, eve5));		
	}
}
