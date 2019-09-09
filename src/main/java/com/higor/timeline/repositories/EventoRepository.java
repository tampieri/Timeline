package com.higor.timeline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.higor.timeline.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>{

	@Query("SELECT ev FROM event ev ORDER BY timestamp DESC")
	List<Evento> findAll();

	@Query("SELECT ev FROM event ev WHERE ev.event like %?1%")
	List<Evento> findByEvent(String event);
}
