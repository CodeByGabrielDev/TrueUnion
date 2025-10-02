package br.com.TrueUnion.TrueUnion.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Repository.EventoRepository;

@Service
public class ServiceEvento {
	@Autowired
	EventoRepository event;

	public void createEvento(Evento evento) {

	}

}
