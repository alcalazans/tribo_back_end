package com.tribo.model.negociacoes;

import com.tribo.model.UsuarioAbstract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MensagemClienteFornecedor {

    private UsuarioAbstract from;
    private UsuarioAbstract to;

    private String mensagem;

    private LocalDateTime datahoraEnvio;

    private LocalDateTime datahoraLeitura;
}
