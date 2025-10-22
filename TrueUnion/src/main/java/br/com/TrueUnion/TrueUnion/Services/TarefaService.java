package br.com.TrueUnion.TrueUnion.Services;

import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.TarefaRequest;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Tarefa;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.TarefaResponse;
import br.com.TrueUnion.TrueUnion.Utils.Utils;

@Service
public class TarefaService {

	public void criarTask(TarefaRequest tarefa, String nomeEvento, Iterable<Evento> eventos) {
		Evento evento = Utils.encontrarEventoComBaseNoNome(nomeEvento, eventos);
		if (Utils.validadorDeLocalDate(tarefa.getPrazo())) {
			Tarefa tarefaEntitie = new Tarefa(tarefa.getDesc(), tarefa.getPrazo(), evento);
			tarefaEntitie.setAtrasada(false);
			
		}else {
			throw new IllegalArgumentException("Erro validar!");
		}

	}

}
