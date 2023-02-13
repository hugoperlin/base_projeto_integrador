use app_db;
CREATE TABLE IF NOT EXISTS produtos(
  id int AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  descricao varchar(255) NOT NULL,
  valor real NOT NULL,
  quantidade double NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compradores (
  id int AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL unique,
  email varchar(100) NOT NULL,
  telefone varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vendas (
	id INT AUTO_INCREMENT,
    dataHora TIMESTAMP NULL,
	idComprador INT NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT vendas_FK_comprador FOREIGN KEY (idComprador) REFERENCES compradores(id)
);

CREATE TABLE IF NOT EXISTS itensPedido (
	id INT AUTO_INCREMENT,
    idVenda INT NOT NULL,
	idProduto INT NOT NULL,
    valor double NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT itensvenda_FK_venda FOREIGN KEY (idVenda) REFERENCES vendas(id),
    CONSTRAINT itensvenda_FK_produto FOREIGN KEY (idProduto) REFERENCES produtos(id)
);