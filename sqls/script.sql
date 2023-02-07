 CREATE TABLE IF NOT EXISTS contatos{
    id INTEGER PRIMARY_KEY,
    nome TEXT NOT NULL,
    telefone TEXT NOT NULL,
    email TEXT NOT NULL
}

CREATE TABLE IF NOT EXISTS produtos{
    id INTEGER PRIMARY_KEY,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL,
    preco REAL NOT NULL
}