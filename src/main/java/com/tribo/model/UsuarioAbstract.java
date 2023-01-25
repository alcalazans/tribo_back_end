package com.tribo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class UsuarioAbstract {

    private String documento;
    private String nome;
    private String emaail;
    private String senha;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataCancelamento;
    private SituacaoUsuario situacao;



}
