INSERT INTO usuario (nome, email, senha, cpf, ativo) VALUES
('Mariazinha', 'mariazinha@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '123', true),
('Joãozinho', 'joaozinho@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '345', true),
('Mestre', 'mestre@email.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '456', true),
('Inativo', 'inativo@gmail.com', '$2y$12$J6gPQvpysLzecMOeVaSXpeHNY4.MtI9FM9fjD18KNslMk6uox9yvS', '000', FALSE);

INSERT INTO papel (name) VALUES
('USER'),
('ADMIN');

INSERT INTO usuario_roles (users_id, roles_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(3, 2),
(4, 1);
