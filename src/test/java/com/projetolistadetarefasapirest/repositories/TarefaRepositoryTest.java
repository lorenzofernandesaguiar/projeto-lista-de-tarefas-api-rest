package com.projetolistadetarefasapirest.repositories;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.projetolistadetarefasapirest.entities.Tarefa;

@DataJpaTest
@DisplayName("TarefaRepositoryTest")
public class TarefaRepositoryTest {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	@DisplayName("deve buscar todas as tarefas salvas no banco de dados com sucesso")
	public void deveBuscarTodasAsTarefasSalvasNoBancoDeDadosComSucesso() {
		
		Tarefa tarefa1 = new Tarefa("Agendar reunião", false);
		Tarefa tarefa2 = new Tarefa("Ler um livro", false);
		Tarefa tarefa3 = new Tarefa("Concluir relatório", false);
		
		// Salva três tarefas no banco de dados
		Tarefa tarefaSalva1 = this.tarefaRepository.save(tarefa1);
		Tarefa tarefaSalva2 = this.tarefaRepository.save(tarefa2);
		Tarefa tarefaSalva3 = this.tarefaRepository.save(tarefa3);
		
		// Busca todas as tarefas que foram salvas no banco de dados. O retorno é guardado em uma
		// lista chamada listaDeTarefasSalvas
		List<Tarefa> listaDeTarefasSalvas = this.tarefaRepository.findAll();
		
		// Verifica se listaDeTarefasSalvas não é nula
		Assertions.assertThat(listaDeTarefasSalvas).isNotNull();
		
		// Verifica se todas as tarefas que foram salvas no banco de dados estão presentes em
		// listaDeTarefasSalvas
		Assertions.assertThat(listaDeTarefasSalvas.get(0)).isEqualTo(tarefaSalva1);
		Assertions.assertThat(listaDeTarefasSalvas.get(1)).isEqualTo(tarefaSalva2);
		Assertions.assertThat(listaDeTarefasSalvas.get(2)).isEqualTo(tarefaSalva3);
		
	}
	
	@Test
	@DisplayName("deve buscar uma tarefa pelo id no banco de dados com sucesso")
	public void deveBuscarUmaTarefaPeloIdNoBancoDeDadosComSucesso() {
		
		Tarefa tarefa = new Tarefa("Agendar reunião", false);
		
		// Salva uma tarefa no banco de dados
		Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
		
		// Busca no banco de dados a tarefa que foi salva anteriormente. O retorno é guardado no
		// objeto da classe Tarefa chamado tarefaBuscada.
		Tarefa tarefaBuscada = this.tarefaRepository.findById(tarefaSalva.getId()).get();
		
		// Verifica se o objeto tarefaBuscada não é nulo
		Assertions.assertThat(tarefaBuscada).isNotNull();
		
		// Verifica se a tarefa buscada do banco de dados é igual à tarefa que havia sido salva no
		// banco de dados
		Assertions.assertThat(tarefaBuscada).isEqualTo(tarefaSalva);
		
	}
	
	@Test
	@DisplayName("deve buscar uma tarefa pelo id no banco de dados com falha")
	public void deveBuscarUmaTarefaPeloIdNoBancoDeDadosComFalha() {
		
		// Busca uma tarefa que não existe no banco de dados. O retorno é guadado em um Optional
		// chamado tarefaOptional
		Optional<Tarefa> tarefaOptional = this.tarefaRepository.findById(1L);
		
		// Verifica se não existe um valor presente em tarefaOptional
		Assertions.assertThat(tarefaOptional).isNotPresent();
		
	}
	
	@Test
	@DisplayName("deve salvar uma tarefa no banco de dados com sucesso")
	public void deveSalvarUmaTarefaNoBancoDeDadosComSucesso() {
		
		Tarefa tarefa = new Tarefa("Agendar reunião", false);
		
		// Salva uma tarefa no banco de dados
		Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
		
		// Verifica se não é nula a tarefa que foi salva no banco de dados
		Assertions.assertThat(tarefaSalva).isNotNull();
		
		// Verifica se foi gerado um id para a tarefa que foi salva no banco de dados
		Assertions.assertThat(tarefaSalva.getId()).isNotNull();
		
		// Verifica se estão com os valores certos os campos descricao e concluida da tarefa que
		// foi salva no banco de dados 
		Assertions.assertThat(tarefaSalva.getDescricao()).isEqualTo(tarefa.getDescricao());
		Assertions.assertThat(tarefaSalva.getConcluida()).isEqualTo(tarefa.getConcluida());
		
	}
	
	@DisplayName("deve editar uma tarefa no banco de dados com sucesso")
	public void deveEditarUmaTarefaComSucesso() {
		
		Tarefa tarefa = new Tarefa("Agendar reunião", false);
		
		// Salva uma tarefa no banco de dados
		Tarefa tarefaSalvaOriginal = this.tarefaRepository.save(tarefa);
		
		Long id = tarefaSalvaOriginal.getId();
		
		tarefaSalvaOriginal.setDescricao("Ler um livro");
		tarefaSalvaOriginal.setConcluida(true);
		
		// Atualiza o valor dos campos descricao e concluida da tarefa que havia sido salva 
		Tarefa tarefaAtualizada = this.tarefaRepository.save(tarefaSalvaOriginal);
		
		// Verifica se a tarefa que foi atualizada conservou o id que tinha antes da atualização
		Assertions.assertThat(tarefaAtualizada.getId()).isEqualTo(id);
		
		// Verifica se a tarefa que foi atualizada persistiu com sucesso as alterações que haviam
		// sido promovidas
		Assertions.assertThat(tarefaAtualizada.getDescricao()).isEqualTo("Ler um livro");
		Assertions.assertThat(tarefaAtualizada.getConcluida()).isEqualTo(true);
		
	}
	
	@Test
	@DisplayName("deve excluir uma tarefa no banco de dados com sucesso")
	public void deveExcluirrUmaTarefaComSucesso() {
		
		Tarefa tarefa = new Tarefa("Agendar reunião", false);
		
		// Salva uma tarefa no banco de dados
		Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
		
		Long id = tarefaSalva.getId();
		
		// Deleta a tarefa que havia sido salva no banco de dados
		this.tarefaRepository.delete(tarefaSalva);
		
		// Busca a tarefa que foi deletada do banco de dados. O retorno é guadado em um Optional
		// chamado tarefaOptional
		Optional<Tarefa> tarefaOptional = this.tarefaRepository.findById(id);
		
		// Verifica se não existe um valor presente em tarefaOptional
		Assertions.assertThat(tarefaOptional).isNotPresent();
		
	}
	
}