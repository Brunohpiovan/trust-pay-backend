CREATE TABLE usuario (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

  nome VARCHAR(255) NOT NULL,
  login VARCHAR(255) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  celular VARCHAR(15) NOT NULL,

  cargo BIT(1) NOT NULL,

  saldo DECIMAL(19,2) NOT NULL,

  chavePix VARCHAR(255) NOT NULL,
  senha_transferencia VARCHAR(255),

  CONSTRAINT unique_usuario_login UNIQUE (login),
  CONSTRAINT unique_usuario_cpf UNIQUE (cpf),
  CONSTRAINT unique_usuario_celular UNIQUE (celular),
  CONSTRAINT unique_usuario_chavePix UNIQUE (chavePix),

  CONSTRAINT check_usuario_cargo CHECK (cargo IN (0, 1))
);
