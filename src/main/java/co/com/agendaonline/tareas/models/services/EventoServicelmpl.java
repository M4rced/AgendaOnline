package co.com.agendaonline.tareas.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.agendaonline.tareas.models.dao.EventoDAO;
import co.com.agendaonline.tareas.models.entities.Evento;
@Service
public class EventoServicelmpl implements EventoService {
	
	private final EventoDAO eventoDAO;
	
	
	public EventoServicelmpl(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Evento> findAll() {
		return (List<Evento>) eventoDAO.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Evento> findAll(Pageable pageable) {
		return eventoDAO.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Evento findOne(Long id) {
		return eventoDAO.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public void save(Evento evento) {
		eventoDAO.save(evento);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		eventoDAO.deleteById(id);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return eventoDAO.count();
	}

}

/*** creado por M4rced and G3ors ***/
