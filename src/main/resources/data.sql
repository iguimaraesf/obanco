INSERT INTO correntista (nome, email, senha, cpf, ativo) VALUES
('Mariazinha', 'mariazinha@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '123', true),
('Joãozinho', 'joaozinho@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '345', true),
('Mestre', 'mestre@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '456', true),
('Inativo', 'inativo@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '000', FALSE);

INSERT INTO papel (name) VALUES
('USER'),
('ADMIN');

INSERT INTO correntista_papel (correntista_id, papel_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(3, 2),
(4, 1);

INSERT INTO conta (correntista_id) VALUES
(1),
(2),
(3),
(3);

INSERT INTO historico (hora, conta_id, valor, descricao) VALUES
(clock_timestamp(), 3, 1000, 'Saldo de teste do admin'),
(clock_timestamp(), 1, 199, 'Teste inicial');
