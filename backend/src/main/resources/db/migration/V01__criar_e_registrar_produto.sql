
CREATE TABLE produto (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	quantidade BIGINT(20) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	ativo BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto (nome, quantidade, valor, ativo) values ('Item 1', 10, 10.10, 1);
INSERT INTO produto (nome, quantidade, valor, ativo) values ('Item 2', 20, 20.20, 1);
INSERT INTO produto (nome, quantidade, valor, ativo) values ('Item 3', 30, 30.30, 1);
INSERT INTO produto (nome, quantidade, valor, ativo) values ('Item 4', 40, 40.40, 1);
INSERT INTO produto (nome, quantidade, valor, ativo) values ('Item 5', 50, 50.50, 1);