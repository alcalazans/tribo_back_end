
INSERT INTO tribo.papel (nome, descricao, situacao, data_criacao) VALUES ('CONSULTAR_SERVICOS', 'Consultar serviços cadastrados', 0, '01/01/2021 10:00');

INSERT INTO tribo.papel (nome, descricao, situacao, data_criacao) VALUES ('SOLICITAR_CONTRATO', 'Solicitar contrato', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil (nome, descricao, situacao, data_criacao) VALUES ('USUARIO_COMUM', 'Usuário Comum', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil (nome, descricao, situacao, data_criacao) VALUES ('USUARIO_ADMIN', 'Usuário Administrador', 0, '01/01/2021 10:00');

INSERT INTO tribo.perfil_papel (id_perfil, id_papel) VALUES (1, 1);

INSERT INTO tribo.perfil_papel (id_perfil, id_papel) VALUES (1, 2);

INSERT INTO tribo.perfil_papel (id_perfil, id_papel) VALUES (2, 1);

INSERT INTO tribo.perfil_papel (id_perfil, id_papel) VALUES (2, 2);

INSERT INTO tribo.usuario (nome, email, cpf, senha, situacao, data_criacao, data_cancelamento)
VALUES('Alexandre Calazans Pereira do Amaral', 'alexandre.calazans@gmail.com', '83847677187', '453d161e8fb2b1a7118a2fc4811b70f8d57f5c9d', 0, '09/02/2021 10:00', null);

INSERT INTO tribo.usuario (nome, email, cpf, senha, situacao, data_criacao, data_cancelamento)
VALUES('Jonh Lenon', 'johnl@gmail.com', '03029899878', '453d161e8fb2b1a7118a2fc4811b70f8d57f5c9d', 0, '09/02/2021 10:00', null);

INSERT INTO tribo.usuario_perfil (id_usuario, id_perfil)
VALUES(1, 1);

INSERT INTO tribo.usuario_perfil (id_usuario, id_perfil)
VALUES(2, 1);