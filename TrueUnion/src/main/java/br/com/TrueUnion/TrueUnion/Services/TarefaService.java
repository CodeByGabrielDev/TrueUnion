package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.TarefaRequest;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Tarefa;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryTarefa;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.TarefaResponse;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class TarefaService {

	@Autowired
	RepositoryTarefa tarefa;

	@Transactional
	public TarefaResponse criarTask(TarefaRequest tarefa, String nomeEvento, Iterable<Evento> eventos) {
		Evento evento = Utils.encontrarEventoComBaseNoNome(nomeEvento, eventos);
		if (Utils.validadorDeLocalDate(tarefa.getPrazo())) {
			Tarefa tarefaEntitie = new Tarefa(tarefa.getDesc(), tarefa.getPrazo(), evento);
			tarefaEntitie.setAtrasada(false);
			TarefaResponse respostaEmTela = new TarefaResponse(tarefaEntitie.getDesc(), tarefaEntitie.getPrazo(),
					tarefaEntitie.getEvento().getNome(), false);
			this.tarefa.save(tarefaEntitie);
			return respostaEmTela;

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro validar!");
		}

	}

	public List<TarefaResponse> listarTarefasPorEvento(Iterable<Evento> eventosDoUsuarioAtual) {
		List<TarefaResponse> listaDeTarefas = new ArrayList<>();
		for (Evento evento : eventosDoUsuarioAtual) {
			if (evento != null) {
				List<Tarefa> listaDeTarefasBaseadoNoEvento = this.tarefa.findTarefasByEvento(evento);
				for (Tarefa tarefa : listaDeTarefasBaseadoNoEvento) {
					TarefaResponse respostaEmTela = new TarefaResponse(tarefa.getDesc(), tarefa.getPrazo(),
							tarefa.getEvento().getNome(), false);
					listaDeTarefas.add(respostaEmTela);
				}
			}
		}
		return listaDeTarefas;
	}

}
