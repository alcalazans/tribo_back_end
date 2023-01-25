package com.tribo.model;

import lombok.Data;
import net.jcip.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Immutable
@Table(name = "perfil", schema = "tribo")
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
	@SequenceGenerator(name = "sq_perfil", sequenceName = "sq_perfil", schema = "tribo", allocationSize = 1)
	private Long id;

	private String nome;

	@Column
	private String descricao;

	@Column
	private Integer situacao;

	@Column
	private LocalDateTime data_criacao;

	@Override
	public String getAuthority() {
		return this.getNome();
	}

}
