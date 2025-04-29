package com.projetolistadetarefasapirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetolistadetarefasapirest.dto.TarefaDTO;
import com.projetolistadetarefasapirest.entities.Tarefa;
import com.projetolistadetarefasapirest.repositories.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public ResponseEntity<List<TarefaDTO>> buscarTodasAsTarefas() {
		
		// Busca todas as tarefas salvas no banco de dados e guarda elas em uma lista. Cada
		// elemento dessa lista é um objeto da classe Tarefa
		List<Tarefa> listTarefaEntity = tarefaRepository.findAll();
		
		// Converte a lista anterior em uma nova lista. Cada elemento dessa nova lista é um objeto
		// da classe TarefaDTO
		List<TarefaDTO> listTarefaDTO = listTarefaEntity.stream().map(x -> new TarefaDTO(x)).toList();
		
		// Retorna um ResponseEntity, de modo que o corpo da resposta HTTP é a lista que contém
		// objetos da classe TarefaDTO. Já o código de status da resposta HTTP é 200
		return new ResponseEntity<List<TarefaDTO>>(listTarefaDTO, HttpStatus.OK);
		
	}
	
	public ResponseEntity<TarefaDTO> buscarTarefaPeloId(Long id) {
		
		// Busca uma tarefa no banco de dados pelo seu id. O retorno é guardado em um Optional
		// chamado tarefaEntityOptional
		Optional<Tarefa> tarefaEntityOptional = tarefaRepository.findById(id);
		
        if(tarefaEntityOptional.isPresent()) {
        	
        	// Se existir uma tarefa com o id informado, guarda o valor de tarefaEntityOptional em
        	// um objeto da classe Tarefa chamado tarefaEntity 
        	Tarefa tarefaEntity = tarefaEntityOptional.get();
        	
        	// Converte o objeto tarefaEntity em um objeto da classe TarefaDTO chamado tarefaDTO
        	TarefaDTO tarefaDTO = new TarefaDTO(tarefaEntity);
        	
        	// Retorna um ResponseEntity, de modo que o corpo da resposta HTTP é o objeto
        	// tarefaDTO. Já o código de status da resposta HTTP é 200
        	return new ResponseEntity<TarefaDTO>(tarefaDTO, HttpStatus.OK);
        	
        } else {
        	
        	// Se não existir uma tarefa com o id informado, retorna um ResponseEntity, de modo que
        	// o código de status da resposta HTTP é 404
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        	
        }
        
	}
	
	public ResponseEntity<TarefaDTO> adicionarTarefa(TarefaDTO tarefaDTO) {
		
		// Cria um objeto da classe Tarefa chamado tarefaEntity. O atributo id de tarefaEntity foi
		// deixado nulo, pois o id será gerado pelo banco de dados. O atributo descricao de
		// tarefaEntity é igual ao atributo descricao do objeto da classe TarefaDTO chamado
		// tarefaDTO. O atributo concluida de tarefaEntity é igual a false, pois, assim que uma
		// tarefa é criada, essa tarefa não está concluída
		Tarefa tarefaEntity = new Tarefa();
		tarefaEntity.setDescricao(tarefaDTO.getDescricao());
		tarefaEntity.setConcluida(false);
		
		// Salva a tarefa representada pelo objeto tarefaEntity no banco de dados. O retorno é
		// guardado no objeto da classe Tarefa chamado tarefaEntityAdicionada 
		Tarefa tarefaEntityAdicionada = tarefaRepository.save(tarefaEntity);
		
		// Converte o objeto tarefaEntityAdicionada em um objeto da classe TarefaDTO chamado
		// tarefaDTOAdicionada
		TarefaDTO tarefaDTOAdicionada = new TarefaDTO(tarefaEntityAdicionada);
		
		// Retorna um ResponseEntity, de modo que o corpo da resposta HTTP é o objeto
		// tarefaDTOAdicionada. Já o código de status da resposta HTTP é 201
		return new ResponseEntity<TarefaDTO>(tarefaDTOAdicionada, HttpStatus.CREATED);
		
	}

	public ResponseEntity<TarefaDTO> editarTarefa(TarefaDTO tarefaDTO) {
	
		// Busca uma tarefa no banco de dados pelo id do objeto da classe TarefaDTO chamado
		// tarefaDTO. O retorno é guardado em um Optional chamado tarefaEntityOptional
		Optional<Tarefa> tarefaEntityOptional = tarefaRepository.findById(tarefaDTO.getId());
		
		if(tarefaEntityOptional.isPresent()) {
			
			// Se existir uma tarefa com o id informado, guarda o valor de tarefaEntityOptional em
			// um objeto da classe Tarefa chamado tarefaEntity
			Tarefa tarefaEntity = tarefaEntityOptional.get();
			
			// Atualiza os valores dos atributos pertencentes ao objeto tarefaEntity de acordo com
			// os valores dos respectivos atributos pertencentes ao objeto tarefaDTO. O atributo id
			// de tarefaEntity não deve ser alterado
			tarefaEntity.setDescricao(tarefaDTO.getDescricao());
			tarefaEntity.setConcluida(tarefaDTO.getConcluida());
			
			// Atualiza a tarefa em questão no banco de dados. O retorno é guardado em um objeto da
			// classe Tarefa chamado tarefaEntityEditada
			Tarefa tarefaEntityEditada = tarefaRepository.save(tarefaEntity);
			
			// Converte o objeto tarefaEntityEditada em um objeto da classe TarefaDTO chamado
			// tarefaDTOEditada
			TarefaDTO tarefaDTOEditada = new TarefaDTO(tarefaEntityEditada);
			
			// Retorna um ResponseEntity, de modo que o corpo da resposta HTTP é o objeto
			// tarefaDTOEditada. Já o código de status da resposta HTTP é 200
			return new ResponseEntity<TarefaDTO>(tarefaDTOEditada, HttpStatus.OK);
			
		} else {
			
			// Se não existir uma tarefa com o id informado, retorna um ResponseEntity, de modo que
			// o código de status da resposta HTTP é 404
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	public ResponseEntity<Object> excluirTarefa(Long id) {
		
		// Busca uma tarefa no banco de dados pelo id informado. O retorno é guardado em um
		// Optional chamado tarefaEntityOptional
		Optional<Tarefa> tarefaEntityOptional = tarefaRepository.findById(id);
		
		if(tarefaEntityOptional.isPresent()) {
			
			// Se existir uma tarefa com o id informado, guarda o valor de tarefaEntityOptional em
			// um objeto da classe Tarefa chamado tarefaEntity
			Tarefa tarefaEntity = tarefaEntityOptional.get();
			
			// Deleta do banco de dados a tarefa representada pelo objeto tarefaEntity
			tarefaRepository.delete(tarefaEntity);
			
			// Retorna um ResponseEntity, de modo que o código de status da resposta HTTP é 204
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} else {
			
			// Se não existir uma tarefa com o id informado, retorna um ResponseEntity, de modo que
			// o código de status da resposta HTTP é 404
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
}