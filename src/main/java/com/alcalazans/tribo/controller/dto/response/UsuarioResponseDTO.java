package com.alcalazans.tribo.controller.dto.response;

import com.alcalazans.tribo.model.Perfil;
import com.alcalazans.tribo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioResponseDTO {
    private Long id;
    private List<Perfil> perfis = new ArrayList<>();
    private String nome;
    private String email;
    private String cpf;
    private Integer situacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataCancelamento;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.perfis = usuario.getPerfis();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.situacao = usuario.getSituacao();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataCancelamento = usuario.getDataCancelamento();
    }

}
