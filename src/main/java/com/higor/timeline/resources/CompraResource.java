package com.higor.timeline.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.higor.timeline.domain.Compra;
import com.higor.timeline.domain.CustomData;
import com.higor.timeline.domain.Evento;
import com.higor.timeline.domain.Produto;
import com.higor.timeline.services.EventoService;

@RestController
@RequestMapping(value="/timeline")
public class CompraResource {
	
	@Autowired
	private EventoService eventoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Compra>> findAll() {
		List<Evento> listaEventos = eventoService.findAll();
		List<Compra> listaCompras = new ArrayList<>();
		CustomData cdti1 = new CustomData();
		
		if(!listaEventos.isEmpty()) {
			for (Evento evento : listaEventos) {
				List<Evento> lista= new ArrayList<>();
				List<CustomData> cd = evento.getCustom_data();
				CustomData cdti = buscaId(cd);
				for (Evento evento2 : listaEventos) {
					List<CustomData> cd2 = evento2.getCustom_data();
					CustomData cdtiAux = buscaId(cd2);
					if(cdti.getKey().equals("transaction_id") && 
							cdti.getValue().equals(cdtiAux.getValue())){
						lista.add(evento2);
						cdti1 = cdtiAux;
					}
				}
				if(listaCompras.isEmpty() || 
						buscaNalistaCompras(listaCompras, cdti1.getValue())) {
					listaCompras.add(retornaCompra(lista));
				}
			}
		}
		return ResponseEntity.ok().body(listaCompras);
	}
	
	private CustomData buscaId(List<CustomData> cd) {
		CustomData c = new CustomData();
		for (CustomData customData : cd) {
			if(customData.getKey().equals("transaction_id")){
				c.setKey(customData.getKey());
				c.setValue(customData.getValue());
			}
		}
		return c;
	}

	private boolean buscaNalistaCompras(List<Compra> listaCompras, String value) {
		boolean ret = false;
		for (Compra compra : listaCompras) {
			if(compra.getTransaction_id().equals(value)) {
				ret = false;
			}else {
				ret = true;
			}
		}
		return ret;
	}

	public Compra retornaCompra(List<Evento> list){
		Compra compra = new Compra();
		
		for(Evento ev : list) {
			compra.setTimestamp(ev.getTimestamp());
			compra.setRevenue(ev.getRevenue());
			Produto pr = new Produto();
			for (CustomData cda : ev.getCustom_data()) {
				
				if(cda.getKey().equals("transaction_id"))
				compra.setTransaction_id(cda.getValue());
				if(cda.getKey().equals("store_name"))
				compra.setStore_name(cda.getValue());
				if(cda.getKey().equals("product_name"))
					if(!cda.getValue().equals(null))
						pr.setName(cda.getValue());
				if(cda.getKey().equals("product_price"))
					if(!cda.getValue().equals(null)) {
						pr.setPrice(Double.parseDouble(cda.getValue()));
						compra.getProducts().add(pr);
					}
			}
		}
		return compra;
	}	
}
