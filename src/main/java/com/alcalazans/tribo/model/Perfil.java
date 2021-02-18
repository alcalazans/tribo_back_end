package com.alcalazans.tribo.model;

import net.jcip.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "perfil", schema = "tribo")
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
	@SequenceGenerator(name = "sq_perfil", sequenceName = "sq_perfil", schema = "tribo", allocationSize = 1)
	private Long id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private Integer situacao;

	@Column
	private LocalDateTime data_criacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(LocalDateTime data_criacao) {
		this.data_criacao = data_criacao;
	}

	@Override
	public String getAuthority() {
		return nome;
	}

}
