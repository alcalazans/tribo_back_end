package com.alcalzans.tribo.controller.dto;

import com.alcalzans.tribo.model.Perfil;
import com.alcalzans.tribo.model.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaUsuarioDTO {
    private Long id;
    private List<Perfil> perfis = new ArrayList<>();
    private String nome;
    private String email;
    private String cpf;
    private String situacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataCancelamento;

    public ConsultaUsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.perfis = usuario.getPerfis();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.situacao = usuario.getSituacao();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataCancelamento = usuario.getDataCancelamento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
}
