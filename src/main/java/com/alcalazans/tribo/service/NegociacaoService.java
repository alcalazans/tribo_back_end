package com.alcalazans.tribo.service;

import com.alcalazans.tribo.model.Cliente;
import com.alcalazans.tribo.model.Fornecedor;
import com.alcalazans.tribo.model.UsuarioAbstract;
import com.alcalazans.tribo.model.negociacoes.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NegociacaoService {
    
    public List<Negocio> getNegocios(){
        return getNegociosRepository();
    }

    private List<Negocio> getNegociosRepository() {
        List<Negocio> negocios = new ArrayList<>();
        negocios.add(buidNegocio());
        return negocios;
    }

    private Negocio buidNegocio() {
        Cliente cliente = buidCliente();
        Fornecedor fornecedor = buidFornecedor();

        Negocio negocio = new Negocio();
        negocio.setCliente(cliente);
        negocio.setFornecedor(fornecedor);
        negocio.setDataInicioNegociacao(LocalDateTime.now());
        negocio.setMensagens(buildMensagens(negocio));
        negocio.setSituacaoNegocio(buildSituacao());
        negocio.setReclamacoes(buildReclamcoes(negocio));
        negocio.setLocalRealizacaoServico(builLocal());

        return negocio;
    }

    private Localizacao builLocal() {
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude("-15.090909");
        localizacao.setLongitude("-53.30390");
        localizacao.setLogradouro(buidLogradouro());
        return localizacao;
    }

    private Logradouro buidLogradouro() {
        Logradouro logradouro = new Logradouro();
        logradouro.setLogradouro("AE 04 conjunto i/j torre 2 apartamento 1802");
        logradouro.setBairro("Guara II");
        logradouro.setCep("71.079-640");
        return logradouro;
    }

    private List<Reclamacao> buildReclamcoes(Negocio negocio) {
        List<Reclamacao> reclamacoes = new ArrayList<>();
        reclamacoes.add(builReclamacao(negocio, "Não apareceu para realizar orćamento."));
        return reclamacoes;
    }

    private Reclamacao builReclamacao(Negocio negocio, String texto) {
        Reclamacao reclamacao = new Reclamacao();
        reclamacao.setNegocio(null);
        reclamacao.setDataReclamacao(LocalDateTime.now());
        reclamacao.setTextoReclamacao(texto);
        reclamacao.setDiscusaoList(buidDiscusoes(reclamacao));
        return reclamacao;
    }

    private List<Discusao> buidDiscusoes(Reclamacao reclamacao) {
        List<Discusao> discusoes = new ArrayList<>();
        discusoes.add(buidDiscusao(reclamacao,
                "Alexandre Calazans" ,
                "ABC Moveis planejados",
                "Você não apareceu para realizar o orćamento."));
        discusoes.add(buidDiscusao(reclamacao,
                "ABC Moveis planejados",
                "Alexandre Calazans" ,
                "Desculpe, posso ir amanhã de manhã?"));
        discusoes.add(buidDiscusao(reclamacao,
                "Alexandre Calazans" ,
                "ABC Moveis planejados",
                "Sim"));
        discusoes.add(buidDiscusao(reclamacao,
                "ABC Moveis planejados",
                "Alexandre Calazans" ,
                "Desculpe, Combinado"));

        return discusoes;
    }

    private Discusao buidDiscusao(Reclamacao reclamacao, String from, String to, String texto) {
        Discusao discusao = new Discusao();
        discusao.setReclamacao(null);
        discusao.setFrom(buidCliente());
        discusao.setTo(buidFornecedor());
        discusao.setMensagem(texto);
        discusao.setDatahoraEnvio(LocalDateTime.now());
        return discusao;
    }

    private SituacaoNegocio buildSituacao() {
        SituacaoNegocio situacaoNegocio = new SituacaoNegocio();
        situacaoNegocio.setId(1);
        situacaoNegocio.setDescricao("Forncedor respondeu a primeira mensagem");
        situacaoNegocio.setNome("Em atendimento");

        return situacaoNegocio;
    }

    private List<MensagemClienteFornecedor> buildMensagens(Negocio negocio){
        List<MensagemClienteFornecedor> mensagens = new ArrayList<>();
        mensagens.add(buildMensagem(negocio.getCliente(), negocio.getFornecedor(), "Vamos fazer um orćamento?"));
        mensagens.add(buildMensagem(negocio.getFornecedor(), negocio.getCliente(), "Podemos sim. Pode ser na segunda as 14h."));

        return mensagens;
    }

    private MensagemClienteFornecedor buildMensagem(UsuarioAbstract from, 
                                                    UsuarioAbstract to, String texto) {
        MensagemClienteFornecedor mensagem = new MensagemClienteFornecedor();
        mensagem.setFrom(from);
        mensagem.setTo(to);
        mensagem.setMensagem(texto);
        mensagem.setDatahoraEnvio(LocalDateTime.now());
        mensagem.setDatahoraLeitura(LocalDateTime.now());
        
        return mensagem;
    }

    private Fornecedor buidFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setDocumento("12283745753928");
        fornecedor.setNome("ABC Moveis planejados");
        fornecedor.setEmaail("abcimoveis@gmail.com");
        return fornecedor;
    }

    private Cliente buidCliente() {
        Cliente cliente = new Cliente();
        cliente.setDocumento("83847677187");
        cliente.setNome("Alexandre Calazans");
        cliente.setEmaail("alexandre.calazans@gmail.com");

        return cliente;
    }
}
