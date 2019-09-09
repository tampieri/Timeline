package com.higor.timeline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higor.timeline.domain.Evento;
import com.higor.timeline.repositories.CustomDataRepository;
import com.higor.timeline.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repo;
	
	@Autowired
	private CustomDataRepository customDataRepository;
	
	public List<Evento> findAll() {
		return repo.findAll();
	}
	
	public Page<Evento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	@Transactional
	public Evento insert(Evento obj) {
		obj = repo.save(obj);
		customDataRepository.saveAll(obj.getCustom_data());
		return obj;
	}

	@Transactional
	public List<Evento> findByEvent(String event) {
		List<Evento> listObj = repo.findByEvent(event);
		return listObj;
	}
}
