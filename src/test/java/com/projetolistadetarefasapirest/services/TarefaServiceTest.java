package com.projetolistadetarefasapirest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projetolistadetarefasapirest.dto.TarefaDTO;
import com.projetolistadetarefasapirest.entities.Tarefa;
import com.projetolistadetarefasapirest.repositories.TarefaRepository;

@SpringBootTest
@DisplayName("TarefaServiceTest")
public class TarefaServiceTest {
	
	@Autowired
	private TarefaService tarefaService;
	
	@MockBean
	private TarefaRepository tarefaRepository;
	
	@Test
	@DisplayName("deve buscar todas as tarefas com sucesso")
	public void deveBuscarTodasAsTarefasComSucesso() {
		
		Tarefa tarefa1 = new Tarefa(1L, "Agendar reunião", false);
		Tarefa tarefa2 = new Tarefa(2L, "Ler um livro", false);
		Tarefa tarefa3 = new Tarefa(3L, "Concluir relatório", false);
		
		// Cria uma lista chamada listaDeTarefas. Nessa lista são adicionados objetos da classe
		// Tarefa
		List<Tarefa> listaDeTarefas = new ArrayList<Tarefa>();
		listaDeTarefas.add(tarefa1);
		listaDeTarefas.add(tarefa2);
		listaDeTarefas.add(tarefa3);
		
		// Converte listaDeTarefas em uma lista chamada listaDeTarefasDTO. Os objetos da lista
		// listaDeTarefasDTO são da classe TarefaDTO
		List<TarefaDTO> listaDeTarefasDTO = listaDeTarefas.stream().map(x -> new TarefaDTO(x)).toList();
		
		// Informa que, quando for chamado o método findAll do repositório tarefaRepository, deve
		// ser retornada listaDeTarefas
		Mockito.when(this.tarefaRepository.findAll()).thenReturn(listaDeTarefas);
		
		// Chama o método buscarTodasAsTarefas do serviço tarefaService. O retorno é guardado em um
		// ResponseEntity chamado responseEntity
		ResponseEntity<List<TarefaDTO>> responseEntity = this.tarefaService.buscarTodasAsTarefas();
		
		// Verifica se responseEntity retorna 200 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// Verifica se responseEntity retorna como corpo da resposta HTTP todos os objetos contidos
		// em listaDeTarefasDTO
		Assertions.assertThat(responseEntity.getBody().get(0).toString()).isEqualTo(listaDeTarefasDTO.get(0).toString());
		Assertions.assertThat(responseEntity.getBody().get(1).toString()).isEqualTo(listaDeTarefasDTO.get(1).toString());
		Assertions.assertThat(responseEntity.getBody().get(2).toString()).isEqualTo(listaDeTarefasDTO.get(2).toString());
		
	}
	
	@Test
	@DisplayName("deve buscar uma tarefa pelo id com sucesso")
	public void deveBuscarUmaTarefaPeloIdComSucesso() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado para esse método o parâmetro 1L, deve ser retornado um Optional que traz como
		// valor um objeto da classe Tarefa com id igual a 1L, descricao igual a "Agendar reunião"
		// e concluida igual a false
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.of(new Tarefa(1L, "Agendar reunião", false)));
		
		// Chama o método buscarTarefaPeloId do serviço tarefaService e passa para esse método o
		// parâmetro 1L. O retorno é guardado em um ResponseEntity chamado responseEntity
		ResponseEntity<TarefaDTO> responseEntity = this.tarefaService.buscarTarefaPeloId(1L);
		
		// Verifica se responseEntity retorna 200 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// Verifica se responseEntity retorna como corpo da resposta HTTP um objeto da classe
		// TarefaDTO com id igual a 1L, descricao igual a "Agendar reunião" e concluida igual a
		// false
		Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
		Assertions.assertThat(responseEntity.getBody().getDescricao()).isEqualTo("Agendar reunião");
		Assertions.assertThat(responseEntity.getBody().getConcluida()).isEqualTo(false);
		
	}
	
	@Test
	@DisplayName("deve buscar uma tarefa pelo id com falha")
	public void deveBuscarUmaTarefaPeloIdComFalha() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado para esse método o parâmetro 1L, deve ser retornado um Optional vazio
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.empty());
		
		// Chama o método buscarTarefaPeloId do serviço tarefaService e passa para esse método o
		// parâmetro 1L. O retorno é guardado em um ResponseEntity chamado responseEntity
		ResponseEntity<TarefaDTO> responseEntity = this.tarefaService.buscarTarefaPeloId(1L);
		
		// Verifica se responseEntity retorna 404 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		// Verifica se é vazio o corpo da resposta HTTP retornado por responseEntity
		Assertions.assertThat(responseEntity.getBody()).isNull();
		
	}
	
	@Test
	@DisplayName("deve salvar uma tarefa com sucesso")
	public void deveSalvarUmaTarefaComSucesso() {
		
		// Informa que, quando for chamado o método save do repositório tarefaRepository e for
		// passado como parâmetro para esse método qualquer objeto da classe Tarefa, deve ser
		// retornado um objeto da classe Tarefa com id igual a 1L, descricao igual a "Agendar
		// reunião" e concluida igual a false
		Mockito.when(this.tarefaRepository.save(ArgumentMatchers.any(Tarefa.class))).thenReturn(new Tarefa(1L, "Agendar reunião", false));
		
		// Chama o método adicionarTarefa do serviço tarefaService e passa como parâmetro para esse
		// método um objeto da classe TarefaDTO com descricao igual a "Agendar reunião" e concluida
		// igual a false. O retorno é guardado em um ResponseEntity chamado responseEntity
		ResponseEntity<TarefaDTO> responseEntity = this.tarefaService.adicionarTarefa(new TarefaDTO("Agendar reunião", false));
		
		// Verifica se responseEntity retorna 201 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		// Verifica se responseEntity retorna como corpo da resposta HTTP um objeto da classe
		// TarefaDTO com id igual a 1L
		Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
		
	}
	
	@DisplayName("deve editar uma tarefa com sucesso")
	public void deveEditarUmaTarefaComSucesso() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado 1L como parâmetro para esse método, deve ser retornado um Optional que contém um
		// objeto da classe Tarefa com id igual a 1L, descricao igual a "Agendar reunião" e
		// concluida igual a false
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.of(new Tarefa(1L, "Agendar reunião", false)));
		
		// Informa que, quando for chamado o método save do repositório tarefaRepository e for
		// passado como parâmetro para esse método qualquer objeto da classe Tarefa, deve ser
		// retornado um objeto da classe Tarefa com id igual a 1L, descricao igual a "Ler um livro"
		// e concluida igual a false
		Mockito.when(this.tarefaRepository.save(ArgumentMatchers.any(Tarefa.class))).thenReturn(new Tarefa(1L, "Ler um livro", true));
		
		// Chama o método editarTarefa do serviço tarefaService e passa como parâmetro para esse
		// método um objeto da classe TarefaDTO com id igual a 1L, descricao igual a "Ler um livro"
		// e concluida igual a true. O retorno é guardado em um ResponseEntity chamado
		// responseEntity
		ResponseEntity<TarefaDTO> responseEntity = this.tarefaService.editarTarefa(new TarefaDTO(1L, "Ler um livro", true));
		
		// Verifica se responseEntity retorna 200 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// Verifica se responseEntity retorna como corpo da resposta HTTP um objeto da classe
		// TarefaDTO com id igual a 1L, descricao igual a "Ler um livro" e concluida igual a true
		Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
		Assertions.assertThat(responseEntity.getBody().getDescricao()).isEqualTo("Ler um livro");
		Assertions.assertThat(responseEntity.getBody().getConcluida()).isEqualTo(true);
		
	}
	
	@DisplayName("deve editar uma tarefa com falha")
	public void deveEditarUmaTarefaComFalha() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado para esse método o parâmetro 1L, deve ser retornado um Optional vazio
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.empty());
		
		// Chama o método editarTarefa do serviço tarefaService e passa como parâmetro para esse
		// método um objeto da classe TarefaDTO com id igual a 1L, descricao igual a "Ler um livro"
		// e concluida igual a true. O retorno é guardado em um ResponseEntity chamado
		// responseEntity
		ResponseEntity<TarefaDTO> responseEntity = this.tarefaService.editarTarefa(new TarefaDTO(1L, "Ler um livro", true));
		
		// Verifica se responseEntity retorna 404 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		// Verifica se é vazio o corpo da resposta HTTP retornado por responseEntity
		Assertions.assertThat(responseEntity.getBody()).isNull();
		
	}
	
	@Test
	@DisplayName("deve excluir uma tarefa com sucesso")
	public void deveExcluirUmaTarefaComSucesso() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado 1L como parâmetro para esse método, deve ser retornado um Optional que contém um
		// objeto da classe Tarefa com id igual a 1L, descricao igual a "Agendar reunião" e
		// concluida igual a false
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.of(new Tarefa(1L, "Agendar reunião", false)));
		
		// Chama o método excluirTarefa do serviço tarefaService e passa 1L como parâmetro para
		// esse método. O retorno é guardado em um ResponseEntity chamado responseEntity
		ResponseEntity<Object> responseEntity = this.tarefaService.excluirTarefa(1L);
		
		// Verifica se responseEntity retorna 204 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
		// Verifica se é vazio o corpo da resposta HTTP retornado por responseEntity
		Assertions.assertThat(responseEntity.getBody()).isNull();
		
	}
	
	@Test
	@DisplayName("deve excluir uma tarefa com falha")
	public void deveExcluirUmaTarefaComFalha() {
		
		// Informa que, quando for chamado o método findById do repositório tarefaRepository e for
		// passado para esse método o parâmetro 1L, deve ser retornado um Optional vazio
		Mockito.when(this.tarefaRepository.findById(1L)).thenReturn(Optional.empty());
		
		// Chama o método excluirTarefa do serviço tarefaService e passa 1L como parâmetro para
		// esse método. O retorno é guardado em um ResponseEntity chamado responseEntity
		ResponseEntity<Object> responseEntity = this.tarefaService.excluirTarefa(1L);
		
		// Verifica se responseEntity retorna 404 como código de status da resposta HTTP
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		// Verifica se é vazio o corpo da resposta HTTP retornado por responseEntity
		Assertions.assertThat(responseEntity.getBody()).isNull();
		
	}
	
}