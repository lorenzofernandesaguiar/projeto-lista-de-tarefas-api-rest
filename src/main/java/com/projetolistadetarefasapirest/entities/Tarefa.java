package com.projetolistadetarefasapirest.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tarefas")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 25)
	private String descricao;
	
	@Column(nullable = false)
	private Boolean concluida;

	public Tarefa() {
	}

	public Tarefa(Long id, String descricao, Boolean concluida) {
		this.id = id;
		this.descricao = descricao;
		this.concluida = concluida;
	}

	public Tarefa(String descricao, Boolean concluida) {
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}
	
}