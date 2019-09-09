package com.higor.timeline.dto;

import java.io.Serializable;

import com.higor.timeline.domain.Evento;

public class EventDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String event;
	private String timestamp;
	
	public EventDTO() {
	}
	
	public EventDTO(Evento obj) {
		event = obj.getEvent();
		timestamp = obj.getTimestamp();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
