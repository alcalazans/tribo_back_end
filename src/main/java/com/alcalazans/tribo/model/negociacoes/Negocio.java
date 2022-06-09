package com.alcalazans.tribo.model.negociacoes;

import com.alcalazans.tribo.model.Cliente;
import com.alcalazans.tribo.model.Fornecedor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Negocio {

    private Cliente cliente;
    private Fornecedor fornecedor;

    private boolean servicoFinalizadoFornecedor;

    private boolean servicoFinalizadoCliente;

    private Localizacao localRealizacaoServico;

    private Integer notaAvaliacao;

    private SituacaoNegocio situacaoNegocio;

    private LocalDateTime dataInicioNegociacao;
    private LocalDateTime dataFinalizacaoServico;
    private LocalDateTime dataConclusaoNegociacao;

    private List<MensagemClienteFornecedor> mensagens;

    private List<Reclamacao> reclamacoes;

    private boolean hasReclamacoes(){
        return !this.reclamacoes.isEmpty();
    }

    private boolean isServicoAvaliado(){
        return notaAvaliacao != null;
    }

    private boolean isServicoConcluido(){
        return dataFinalizacaoServico != null;
    }


}
