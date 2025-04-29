package com.projetolistadetarefasapirest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetolistadetarefasapirest.dto.TarefaDTO;
import com.projetolistadetarefasapirest.services.TarefaService;

@WebMvcTest(TarefaController.class)
@DisplayName("TarefaControllerTest")
public class TarefaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private TarefaService tarefaService;
	
	@Test
    @DisplayName("deve buscar todas as tarefas com sucesso")
	public void deveBuscarTodasAsTarefasComSucesso() throws Exception {
		
		// Cria uma lista chamada listaDeTarefasDTO
		List<TarefaDTO> listaDeTarefasDTO = new ArrayList<TarefaDTO>();
		
		// Adiciona em listaDeTarefasDTO objetos da classe TarefaDTO
		listaDeTarefasDTO.add(new TarefaDTO(1L, "Agendar reunião", false));
		listaDeTarefasDTO.add(new TarefaDTO(2L, "Ler um livro", true));
		listaDeTarefasDTO.add(new TarefaDTO(3L, "Concluir relatório", false));
		
		// Gera um JSON a partir de listaDeTarefasDTO. O retorno é guardado na variável chamada
		// json
		String json = new ObjectMapper().writeValueAsString(listaDeTarefasDTO);
		
		// Informa que, quando for chamado o método buscarTodasAsTarefas do serviço tarefaService,
		// deve ser retornado um ResponseEntity, de modo que o corpo é listaDeTarefasDTO e o código
		// de staus é 200
		Mockito.when(this.tarefaService.buscarTodasAsTarefas()).thenReturn(new ResponseEntity<List<TarefaDTO>>(listaDeTarefasDTO, HttpStatus.OK));
		
		// Simula uma requisição do tipo GET para http://localhost:8080/tarefas. O retorno é
		// guardado em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.get("/tarefas").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 200
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// Verifica se o conteúdo de respostaMockHttp é igual à variável json
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo(json);
		
	}
	
	@Test
    @DisplayName("deve buscar uma tarefa pelo id com sucesso")
	public void deveBuscarUmaTarefaPeloIdComSucesso() throws Exception {
		
		// Cria um objeto da classe TarefaDTO chamado tarefaDTO
		TarefaDTO tarefaDTO = new TarefaDTO(1L, "Agendar reunião", false);
		
		// Gera um JSON a partir de tarefaDTO. O retorno é guardado na variável chamada json
		String json = new ObjectMapper().writeValueAsString(tarefaDTO);
		
		// Informa que, quando for chamado o método buscarTarefaPeloId do serviço tarefaService e
		// for passado 1L como parâmetro para esse método, deve ser retornado um ResponseEntity, de
		// modo que o corpo é tarefaDTO e o código de status é 200
		Mockito.when(this.tarefaService.buscarTarefaPeloId(1L)).thenReturn(new ResponseEntity<TarefaDTO>(tarefaDTO, HttpStatus.OK));
		
		// Simula uma requisição do tipo GET para http://localhost:8080/tarefas/1. O retorno é
		// guardado em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.get("/tarefas/1").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 200
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// Verifica se o conteúdo de respostaMockHttp é igual à variável json
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo(json);
		
	}
	
	@Test
    @DisplayName("deve buscar uma tarefa pelo id com falha")
	public void deveBuscarUmaTarefaPeloIdComFalha() throws Exception {
		
		// Informa que, quando for chamado o método buscarTarefaPeloId do serviço tarefaService e
		// for passado 1L como parâmetro para esse método, deve ser retornado um ResponseEntity, de
		// modo que o código de status é 404
		Mockito.when(this.tarefaService.buscarTarefaPeloId(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		// Simula uma requisição do tipo GET para http://localhost:8080/tarefas/1. O retorno é
		// guardado em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.get("/tarefas/1").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 404
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		
		// Verifica se o conteúdo de respostaMockHttp é vazio
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo("");
		
	}
	
	@Test
    @DisplayName("deve adicionar uma tarefa com sucesso")
	public void deveAdicionarUmaTarefaComSucesso() throws Exception {
		
		// Cria um objeto da classe TarefaDTO chamado tarefaDTO
		TarefaDTO tarefaDTO = new TarefaDTO(1L, "Agendar reunião", false);
		
		// Cria um objeto da classe TarefaDTO com descricao igual a "Agendar reunião" e concluida
		// igual a false. Depois, gera um JSON a partir desse objeto. O retorno é guardado na
		// variável chamada jsonRequisicao
		String jsonRequisicao = new ObjectMapper().writeValueAsString(new TarefaDTO("Agendar reunião", false));
		
		// Gera um JSON a partir de tarefaDTO. O retorno é guardado na variável chamada json
		String jsonResposta = new ObjectMapper().writeValueAsString(tarefaDTO);
		
		// Informa que, quando for chamado o método adicionarTarefa do serviço tarefaService e for
		// passado qualquer objeto da classe TarefaDTO como parâmetro para esse método, deve ser
		// retornado um ResponseEntity, de modo que o corpo seja igual a tarefaDTO e o código de
		// status seja igual a 201
		Mockito.when(this.tarefaService.adicionarTarefa(ArgumentMatchers.any(TarefaDTO.class))).thenReturn(new ResponseEntity<TarefaDTO>(tarefaDTO, HttpStatus.CREATED));
		
		// Simula uma requisição do tipo POST para http://localhost:8080/tarefas. O corpo da
		// requisição é igual ao conteúdo da variável chamada jsonRequisicao. O retorno é guardado
		// em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.post("/tarefas").contentType(MediaType.APPLICATION_JSON).content(jsonRequisicao)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 201
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		
		// Verifica se o conteúdo de respostaMockHttp é igual ao conteúdo da variável jsonResposta
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo(jsonResposta);
		
	}
	
	@Test
    @DisplayName("deve editar uma tarefa com sucesso")
	public void deveEditarUmaTarefaComSucesso() throws Exception {
		
		// Cria um objeto da classe TarefaDTO chamado tarefaDTO
		TarefaDTO tarefaDTO = new TarefaDTO(1L, "Ler um livro", true);
		
		// Gera um JSON a partir de tarefaDTO. O retorno é guardado na variável chamada json
		String json = new ObjectMapper().writeValueAsString(tarefaDTO);
		
		// Informa que, quando for chamado o método editarTarefa do serviço tarefaService e for
		// passado qualquer objeto da classe TarefaDTO como parâmetro para esse método, deve ser
		// retornado um ResponseEntity, de modo que o corpo seja igual a tarefaDTO e o código de
		// status seja igual a 200
		Mockito.when(this.tarefaService.editarTarefa(ArgumentMatchers.any(TarefaDTO.class))).thenReturn(new ResponseEntity<TarefaDTO>(tarefaDTO, HttpStatus.OK));
		
		// Simula uma requisição do tipo PUT para http://localhost:8080/tarefas/1. O corpo da
		// requisição é igual ao conteúdo da variável json. O retorno é guardado em um
		// MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.put("/tarefas/1").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 200
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// Verifica se o conteúdo de respostaMockHttp é igual ao conteúdo da variável json
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo(json);
		
	}
	
	@Test
    @DisplayName("deve editar uma tarefa com falha")
	public void deveEditarUmaTarefaComFalha() throws Exception {
		
		// Cria um objeto da classe TarefaDTO chamado tarefaDTO
		TarefaDTO tarefaDTO = new TarefaDTO(1L, "Ler um livro", true);
		
		// Gera um JSON a partir de tarefaDTO. O retorno é guardado na variável chamada json
		String json = new ObjectMapper().writeValueAsString(tarefaDTO);
		
		// Informa que, quando for chamado o método editarTarefa do serviço tarefaService e for
		// passado qualquer objeto da classe TarefaDTO como parâmetro para esse método, deve ser
		// retornado um ResponseEntity, de modo que o código de status seja igual a 404
		Mockito.when(this.tarefaService.editarTarefa(ArgumentMatchers.any(TarefaDTO.class))).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		// Simula uma requisição do tipo PUT para http://localhost:8080/tarefas/1. O corpo da
		// requisição é igual ao conteúdo da variável json. O retorno é guardado em um
		// MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.put("/tarefas/1").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 404
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		
		// Verifica se o conteúdo de respostaMockHttp é vazio
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo("");
		
	}
	
	@Test
    @DisplayName("deve excluir uma tarefa com sucesso")
	public void deveExcluirUmaTarefaComSucesso() throws Exception {
		
		// Informa que, quando for chamado o método excluirTarefa do serviço tarefaService e for
		// passado 1L como parâmetro para esse método, deve ser retornado um ResponseEntity, de
		// modo que o código de status seja igual a 204
		Mockito.when(this.tarefaService.excluirTarefa(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		
		// Simula uma requisição do tipo DELETE para http://localhost:8080/tarefas/1. O retorno é
		// guardado em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.delete("/tarefas/1").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 204
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
		
		// Verifica se o conteúdo de respostaMockHttp é vazio
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo("");
		
	}
	
	@Test
    @DisplayName("deve excluir uma tarefa com falha")
	public void deveExcluirUmaTarefaComFalha() throws Exception {
		
		// Informa que, quando for chamado o método excluirTarefa do serviço tarefaService e for
		// passado 1L como parâmetro para esse método, deve ser retornado um ResponseEntity, de
		// modo que o código de status seja igual a 404
		Mockito.when(this.tarefaService.excluirTarefa(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		// Simula uma requisição do tipo DELETE para http://localhost:8080/tarefas/1. O retorno é
		// guardado em um MockHttpServletResponse chamado respostaMockHttp
		MockHttpServletResponse respostaMockHttp = this.mockMvc.perform(MockMvcRequestBuilders.delete("/tarefas/1").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Indica que o charset da resposta deve ser UTF-8
		respostaMockHttp.setCharacterEncoding("UTF-8");
		
		// Verifica se o código de status de respostaMockHttp é 404
		Assertions.assertThat(respostaMockHttp.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		
		// Verifica se o conteúdo de respostaMockHttp é vazio
		Assertions.assertThat(respostaMockHttp.getContentAsString()).isEqualTo("");
		
	}
	
}