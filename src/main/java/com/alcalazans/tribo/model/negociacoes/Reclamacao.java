package com.alcalazans.tribo.model.negociacoes;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Reclamacao {

    private Negocio negocio;

    private String textoReclamacao;

    private List<Discusao> discusaoList;

    private LocalDateTime dataReclamacao;


}
