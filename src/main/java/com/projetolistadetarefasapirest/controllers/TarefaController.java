package com.projetolistadetarefasapirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetolistadetarefasapirest.dto.TarefaDTO;
import com.projetolistadetarefasapirest.services.TarefaService;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	public ResponseEntity<List<TarefaDTO>> buscarTodasAsTarefas() {
		
		return tarefaService.buscarTodasAsTarefas();
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TarefaDTO> buscarTarefaPeloId(@PathVariable Long id) {
		
		return tarefaService.buscarTarefaPeloId(id);
		
	}
	
	@PostMapping
	public ResponseEntity<TarefaDTO> adicionarTarefa(@RequestBody TarefaDTO tarefaDTO) {
		
		return tarefaService.adicionarTarefa(tarefaDTO);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TarefaDTO> editarTarefa(@RequestBody TarefaDTO tarefaDTO) {
		
		return tarefaService.editarTarefa(tarefaDTO);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> excluirTarefa(@PathVariable Long id) {
		
		return tarefaService.excluirTarefa(id);
		
	}
	
}