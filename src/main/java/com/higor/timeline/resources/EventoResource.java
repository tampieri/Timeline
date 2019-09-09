package com.higor.timeline.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.higor.timeline.domain.Evento;
import com.higor.timeline.dto.EventDTO;
import com.higor.timeline.services.EventoService;

@RestController
@RequestMapping(value="/events")
public class EventoResource {

	@Autowired
	private EventoService service;
	
	/*
	@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<List<Evento>> findAll() { 
		List<Evento> obj = service.findAll(); 
		return ResponseEntity.ok().body(obj); 
	}
	*/
	 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EventDTO>> findAll() {
		List<Evento> list = service.findAll();
		List<EventDTO> listDto = list.stream().map(obj -> new EventDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody List<Evento> objsEvent) {
		Evento obj = new Evento();
		if(!objsEvent.isEmpty()) {
			for (Evento evento : objsEvent) {
				obj = service.insert(evento);
			}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> find(@RequestParam(value="value") String event) {
		List<Evento> obj = service.findByEvent(event);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Evento>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="event") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Evento> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	
}
