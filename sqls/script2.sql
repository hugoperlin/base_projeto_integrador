use app_db;
CREATE TABLE IF NOT EXISTS produtos(
  id int UNSIGNED AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  descricao varchar(255) NOT NULL,
  valor real NOT NULL,
  quantidade double NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compradores (
  id int UNSIGNED AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL unique,
  email varchar(100) NOT NULL,
  telefone varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vendas (
	id INT UNSIGNED AUTO_INCREMENT,
    dataHora TIMESTAMP NULL,
	idComprador INT NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT vendas_FK_comprador FOREIGN KEY (idComprador) REFERENCES compradores(id)
);

CREATE TABLE IF NOT EXISTS pedidos (
  id int UNSIGNED AUTO_INCREMENT,
  data DATE NOT NULL,
  valorTotal double NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS itensPedido (
	id INT UNSIGNED AUTO_INCREMENT,
	idProduto INT UNSIGNED NOT NULL,
    quantidade double NOT NULL,
    valor real NOT NULL,
    idPedido INT UNSIGNED,
    PRIMARY KEY (id),
    CONSTRAINT itensvenda_FK_produto FOREIGN KEY (idProduto) REFERENCES produtos(id),
    CONSTRAINT itensvenda_FK_pedido FOREIGN KEY (idPedido) REFERENCES pedidos(id)
);

CREATE TABLE IF NOT EXISTS pedidosprodutos (
  idPedido INT UNSIGNED NOT NULL,
  idProduto INT UNSIGNED NOT NULL,
  PRIMARY KEY (idPedido, idProduto),
  CONSTRAINT intenspedido_FK_pedido
      FOREIGN KEY (idPedido) REFERENCES pedidos(id),
  CONSTRAINT itensPedido_FK_produto
      FOREIGN KEY (idProduto) REFERENCES produtos(id)
);

DELIMITER $$
DROP TRIGGER IF EXISTS quant_produtos_insert $$
CREATE TRIGGER quant_produtos_insert
BEFORE INSERT
ON itensPedido FOR EACH ROW
BEGIN
  DECLARE qnt DOUBLE;

  SELECT quantidade 
  INTO qnt
  FROM produtos
  WHERE id = (new.idProduto);

  IF (qnt < new.quantidade) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Quantidade não disponível";
  END IF; 

END$$    
DELIMITER ;

