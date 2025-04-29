package com.projetolistadetarefasapirest.dto;

import com.projetolistadetarefasapirest.entities.Tarefa;

public class TarefaDTO {
	
	private Long id;
	private String descricao;
	private Boolean concluida;
	
	public TarefaDTO() {
	}
	
	public TarefaDTO(Tarefa tarefaEntity) {
		this.id = tarefaEntity.getId();
		this.descricao = tarefaEntity.getDescricao();
		this.concluida = tarefaEntity.getConcluida();
	}

	public TarefaDTO(Long id, String descricao, Boolean concluida) {
		this.id = id;
		this.descricao = descricao;
		this.concluida = concluida;
	}

	public TarefaDTO(String descricao, Boolean concluida) {
		this.descricao = descricao;
		this.concluida = concluida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}
	
	@Override
	public String toString() {
		return "TarefaDTO [id=" + id + ", descricao=" + descricao + ", concluida=" + concluida + "]";
	}
	
}