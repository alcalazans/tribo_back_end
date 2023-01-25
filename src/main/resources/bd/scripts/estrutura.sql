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