package co.com.agendaonline.tareas.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import co.com.agendaonline.tareas.models.entities.Evento;

public interface EventoService {
	
	public List<Evento> findAll();
	public Page<Evento> findAll(Pageable pageable);
	public Evento findOne(Long id);
	public void save(Evento tarea);
	public void remove(Long id);
	public Long count();
}

/*** creado por M4rced and G3ors ***/