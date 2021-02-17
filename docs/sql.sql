--//create schema tribo;
--//ALTER SCHEMA "Tribo" RENAME TO tribo;
--
--//CREATE EXTENSION pgcrypto;
--//SELECT encode(digest('@kakashi01', 'sha1'), 'hex');

DROP TABLE tribo.agendamento;
DROP TABLE tribo.local;
DROP TABLE tribo.historico_contrato;
DROP TABLE tribo.mensagem;
DROP TABLE tribo.contrato;
DROP TABLE tribo.servico;
DROP TABLE tribo.area_profissional;
DROP TABLE tribo.endereco_usuario;
DROP TABLE tribo.usuario_perfil;
DROP TABLE tribo.perfil_papel;
DROP TABLE tribo.papel;
DROP TABLE tribo.perfil;
DROP TABLE tribo.usuario;

DROP SEQUENCE tribo.sq_usuario;
DROP SEQUENCE tribo.sq_perfil;
DROP SEQUENCE tribo.sq_papel;
DROP SEQUENCE tribo.sq_endereco_usuario;
DROP SEQUENCE tribo.sq_area_profissional;
DROP SEQUENCE tribo.sq_servico;
DROP SEQUENCE tribo.sq_contrato;
DROP SEQUENCE tribo.sq_mensagem;
DROP SEQUENCE tribo.sq_historico_contrato;
DROP SEQUENCE tribo.sq_local;
DROP SEQUENCE tribo.sq_agendamento;


CREATE SEQUENCE tribo.sq_perfil START 1;
CREATE TABLE tribo.perfil (
                              id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_perfil'::regclass),
                              nome varchar(60) NOT NULL,
                              descricao varchar(100) NOT NULL,
                              situacao int4 NULL,
                              data_criacao timestamp NOT null,
                              CONSTRAINT perfil_uniq UNIQUE (nome)
);

CREATE SEQUENCE tribo.sq_papel START 1;
CREATE TABLE tribo.papel (
                             id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_papel'::regclass),
                             nome varchar(60) NOT NULL,
                             descricao varchar(100) NOT NULL,
                             situacao int4 NULL,
                             data_criacao timestamp NOT null,
                             CONSTRAINT papel_uniq UNIQUE (nome)
);

CREATE TABLE tribo.perfil_papel (
                                    id_perfil bigint NOT null,
                                    id_papel bigint NOT null,
                                    CONSTRAINT perfil_papel_uniq UNIQUE (id_perfil, id_papel)
);
ALTER TABLE tribo.perfil_papel ADD CONSTRAINT perfil_fk FOREIGN KEY (id_perfil) REFERENCES tribo.perfil(id);
ALTER TABLE tribo.perfil_papel ADD CONSTRAINT papel_fk FOREIGN KEY (id_papel) REFERENCES tribo.papel(id);

CREATE SEQUENCE tribo.sq_usuario START 1;
CREATE TABLE tribo.usuario (
                               id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_usuario'::regclass),
                               nome varchar(60) NOT NULL,
                               email varchar(60) NOT NULL,
                               cpf varchar(11) NULL,
                               senha varchar(80) NOT NULL,
                               situacao int4 NULL,
                               data_criacao timestamp NOT NULL,
                               data_cancelamento timestamp null,
                               CONSTRAINT usuario_uniq UNIQUE (cpf, email)
);

CREATE TABLE tribo.usuario_perfil (
                                      id_usuario bigint NOT null,
                                      id_perfil bigint NOT null,
                                      CONSTRAINT usuario_perfil_uniq UNIQUE (id_usuario, id_perfil)
);
ALTER TABLE tribo.usuario_perfil ADD CONSTRAINT perfil_fk FOREIGN KEY (id_perfil) REFERENCES tribo.perfil(id);
ALTER TABLE tribo.usuario_perfil ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES tribo.usuario(id);


CREATE SEQUENCE tribo.sq_endereco_usuario START 1;
CREATE TABLE tribo.endereco_usuario (
                                        id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_endereco_usuario'::regclass),
                                        id_usuario bigint NOT null,
                                        logradouro varchar(60) NULL,
                                        endereco_numero varchar(5) NULL,
                                        complemento varchar(60) NULL,
                                        bairro varchar(50) NULL,
                                        cep varchar(8) NOT NULL,
                                        nome_municipio varchar(200) NOT NULL,
                                        pais int2 NULL,
                                        uf varchar(2) NOT null,
                                        CONSTRAINT endereco_usuario_uniq UNIQUE (id_usuario, cep)
);
ALTER TABLE tribo.endereco_usuario ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES tribo.usuario(id);


CREATE SEQUENCE tribo.sq_area_profissional START 1;
CREATE TABLE tribo.area_profissional (
                                         id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_area_profissional'::regclass),
                                         nome varchar(60) NOT NULL,
                                         descricao varchar(200) NOT NULL,
                                         situacao int4 NULL,
                                         data_criacao timestamp NOT null,
                                         CONSTRAINT area_profissional_uniq UNIQUE (nome)
);

CREATE SEQUENCE tribo.sq_servico START 1;
CREATE TABLE tribo.servico (
                               id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_servico'::regclass),
                               nome varchar(60) NOT NULL,
                               descricao varchar(200) NOT NULL,
                               situacao int4 NOT NULL,
                               data_criacao timestamp NOT NULL,
                               id_usuario bigint NOT NULL,
                               id_area_profissional bigint NOT NULL,
                               area_profissional_sugestao varchar(200) NOT NULL,
                               abrangencia int4 NOT NULL,
                               CONSTRAINT servico_uniq UNIQUE (id_usuario, nome)
);
ALTER TABLE tribo.servico ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.servico ADD CONSTRAINT area_profissional_fk FOREIGN KEY (id_area_profissional) REFERENCES tribo.area_profissional(id);

CREATE SEQUENCE tribo.sq_contrato START 1;
CREATE TABLE tribo.contrato (
                                id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_contrato'::regclass),
                                id_servico bigint not null,
                                id_usuario_contratante bigint not null,
                                id_usuario_prestador bigint not null,
                                situacao int4 NOT NULL,
                                data_criacao timestamp NOT NULL,
                                data_conclusao timestamp NULL
);
ALTER TABLE tribo.contrato ADD CONSTRAINT usuario_contratante_fk FOREIGN KEY (id_usuario_contratante) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.contrato ADD CONSTRAINT usuario_prestador_fk FOREIGN KEY (id_usuario_prestador) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.contrato ADD CONSTRAINT servico_fk FOREIGN KEY (id_servico) REFERENCES tribo.servico(id);

CREATE SEQUENCE tribo.sq_mensagem START 1;
CREATE TABLE tribo.mensagem (
                                id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_contrato'::regclass),
                                id_contrato bigint not null,
                                mensagem varchar(300) NOT NULL,
                                id_usuario_remetente bigint not null,
                                id_usuario_receptor bigint not null,
                                situacao int4 NOT NULL,
                                data_envio timestamp NOT NULL,
                                data_leitura timestamp NULL
);
ALTER TABLE tribo.mensagem ADD CONSTRAINT usuario_remetente_fk FOREIGN KEY (id_usuario_remetente) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.mensagem ADD CONSTRAINT usuario_receptor_fk FOREIGN KEY (id_usuario_receptor) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.mensagem ADD CONSTRAINT contrato_fk FOREIGN KEY (id_contrato) REFERENCES tribo.contrato(id);

CREATE SEQUENCE tribo.sq_historico_contrato START 1;
CREATE TABLE tribo.historico_contrato (
                                          id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_historico_contrato'::regclass),
                                          id_contrato bigint not null,
                                          acao int4 NOT NULL,
                                          descricao varchar(300) NOT NULL,
                                          id_usuario bigint not null,
                                          data_criacao timestamp NOT null
);
ALTER TABLE tribo.historico_contrato ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES tribo.usuario(id);
ALTER TABLE tribo.historico_contrato ADD CONSTRAINT contrato_fk FOREIGN KEY (id_contrato) REFERENCES tribo.contrato(id);

CREATE SEQUENCE tribo.sq_local START 1;
CREATE TABLE tribo.local (
                             id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_local'::regclass),
                             id_contrato bigint not null,
                             situacao int4 NOT NULL,
                             data_criacao timestamp NOT null,
                             logradouro varchar(60) NULL,
                             endereco_numero varchar(5) NULL,
                             complemento varchar(60) NULL,
                             bairro varchar(50) NULL,
                             cep varchar(8) NOT NULL,
                             nome_municipio varchar(200) NOT NULL,
                             pais int2 NULL,
                             uf varchar(2) NOT null,
                             CONSTRAINT local_uniq UNIQUE (cep, id_contrato)

);
ALTER TABLE tribo.local ADD CONSTRAINT contrato_fk FOREIGN KEY (id_contrato) REFERENCES tribo.contrato(id);

CREATE SEQUENCE tribo.sq_agendamento START 1;
CREATE TABLE tribo.agendamento (
                                   id bigint PRIMARY KEY DEFAULT nextval('tribo.sq_agendamento'::regclass),
                                   id_contrato bigint not null,
                                   situacao int4 NOT NULL,
                                   data_criacao timestamp NOT null,
                                   data_agendamento timestamp NOT null,
                                   id_local bigint not null
);
ALTER TABLE tribo.agendamento ADD CONSTRAINT local_fk FOREIGN KEY (id_local) REFERENCES tribo.local(id);
ALTER TABLE tribo.agendamento ADD CONSTRAINT contrato_fk FOREIGN KEY (id_contrato) REFERENCES tribo.contrato(id);

----------------------------------------------------------------------------------------------------------

INSERT INTO tribo.papel
(nome, descricao, situacao, data_criacao)
VALUES('CONSULTAR_SERVICOS', 'Consultar serviços cadastrados', 0, '01/01/2021 10:00');

INSERT INTO tribo.papel
(nome, descricao, situacao, data_criacao)
VALUES('SOLICITAR_CONTRATO', 'Solicitar contrato', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil
(nome, descricao, situacao, data_criacao)
VALUES('USUARIO_COMUM', 'Usuário Comum', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil
(nome, descricao, situacao, data_criacao)
VALUES('USUARIO_ADMIN', 'Usuário Administrador', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil_papel
(id_perfil, id_papel)
VALUES(1, 1);

INSERT INTO tribo.perfil_papel
(id_perfil, id_papel)
VALUES(1, 2);

INSERT INTO tribo.perfil_papel
(id_perfil, id_papel)
VALUES(2, 1);

INSERT INTO tribo.perfil_papel
(id_perfil, id_papel)
VALUES(2, 2);

INSERT INTO tribo.usuario
(nome, email, cpf, senha, situacao, data_criacao, data_cancelamento)
VALUES('Alexandre Calazans Pereira do Amaral', 'alexandre.calazans@gmail.com', '83847677187', '453d161e8fb2b1a7118a2fc4811b70f8d57f5c9d', 0, '09/02/2021 10:00', null);

INSERT INTO tribo.usuario
(nome, email, cpf, senha, situacao, data_criacao, data_cancelamento)
VALUES('Jonh Lenon', 'johnl@gmail.com', '03029899878', '453d1623232323232fc4811b70f8d57f5c9d', 0, '09/02/2021 10:00', null);

INSERT INTO tribo.usuario_perfil
(id_usuario, id_perfil)
VALUES(1, 1);

INSERT INTO tribo.usuario_perfil
(id_usuario, id_perfil)
VALUES(2, 1);

select * from tribo.usuario_perfil

select * from tribo.perfil
select * from tribo.usuario


    INSERT INTO tribo.endereco_usuario
(id_usuario, logradouro, endereco_numero, complemento, bairro, cep, nome_municipio, pais, uf)
VALUES(1, 'Área Especial', '4', 'I J Sports Club torre 2 apartamento 1802', 'Guará', '71070640', 'Brasília', 0, 'DF');

INSERT INTO tribo.area_profissional
(nome, descricao, situacao, data_criacao)
VALUES('Marcenaria', 'Marcenaria', 0, '31/12/2020 00:00');

INSERT INTO tribo.servico
(nome, descricao, situacao, data_criacao, id_usuario, id_area_profissional, area_profissional_sugestao, abrangencia)
VALUES('Marcenaria', 'Móveis, reforma de móveis', 0, '02/01/2021 10:00', 2, 1, '', 0);

select u.nome,u.email, s.nome, s.descricao , ap.descricao from tribo.servico s, tribo.area_profissional ap, tribo.usuario u
where ap.id = s.id_area_profissional and s.id_usuario = u.id and ap.id = 1;

INSERT INTO tribo.contrato
(id_servico, id_usuario_contratante, id_usuario_prestador, situacao, data_criacao, data_conclusao)
VALUES(1, 1, 2, 0, '02/02/2021 10:20', '02/02/2021 10:20');

INSERT INTO tribo.historico_contrato
(id_contrato, acao, descricao, id_usuario, data_criacao)
VALUES(1, 0, 'Solicitacao', 1, '02/02/2021 10:20');

INSERT INTO tribo.mensagem
(id_contrato, mensagem, id_usuario_remetente, id_usuario_receptor, situacao, data_envio, data_leitura)
VALUES(1, 'Gostaria de realizar um orçamento', 1, 2, 0, '02/02/2021 10:20', '02/02/2021 10:20');

select c.id as contrato, m.mensagem as mensagem, hc.descricao as historico from tribo.contrato c , tribo.mensagem m , tribo.historico_contrato hc
where c.id = m.id_contrato and c.id = hc.id_contrato and c.id = 1;


INSERT INTO tribo.historico_contrato
(id_contrato, acao, descricao, id_usuario, data_criacao)
VALUES(1, 1, 'Aceito realizar Orcamento', 2, '03/02/2021 10:20');

INSERT INTO tribo.mensagem
(id_contrato, mensagem, id_usuario_remetente, id_usuario_receptor, situacao, data_envio, data_leitura)
VALUES(1, 'Vamos sim realizar o orçamento', 2, 1, 0, '03/02/2021 10:20', '03/02/2021 10:20');

select m.mensagem as mensagem, m.data_envio from tribo.contrato c , tribo.mensagem m
where c.id = m.id_contrato and c.id = 1 order by m.data_envio;

select hc.acao, hc.descricao, hc.data_criacao from tribo.contrato c , tribo.historico_contrato hc
where c.id = hc.id_contrato and c.id = 1 order by hc.data_criacao;


select * from tribo.usuario;
select * from tribo.perfil;

select * from tribo.usuario_perfil;


select
    usuario0_.id as id1_1_0_,
    usuario0_.cpf as cpf2_1_0_,
    usuario0_.data_cancelamento as data_can3_1_0_,
    usuario0_.data_criacao as data_cri4_1_0_,
    usuario0_.email as email5_1_0_,
    usuario0_.nome as nome6_1_0_,
    usuario0_.senha as senha7_1_0_,
    usuario0_.situacao as situacao8_1_0_,
    perfis1_.id_usuario as id_usuar1_2_1_,
    perfil2_.id as id_perfi2_2_1_,
    perfil2_.id as id1_0_2_,
    perfil2_.data_criacao as data_cri2_0_2_,
    perfil2_.descricao as descrica3_0_2_,
    perfil2_.nome as nome4_0_2_,
    perfil2_.situacao as situacao5_0_2_
from
    tribo.usuario usuario0_
        left outer join
    tribo.usuario_perfil perfis1_
    on usuario0_.id=perfis1_.id_usuario
        left outer join
    tribo.perfil perfil2_
    on perfis1_.id_perfil=perfil2_.id
where
        usuario0_.id=1
