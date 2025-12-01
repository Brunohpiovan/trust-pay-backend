CREATE TABLE emprestimo (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

  remetente_id BIGINT NOT NULL,

  data_hora DATETIME NOT NULL,

  valor DECIMAL(19,2) NOT NULL,

  numero_parcelas INT NOT NULL,

  juros DOUBLE NOT NULL,

  situacao_emprestimo ENUM('ABERTO', 'APROVADO', 'REPROVADO') NOT NULL,

  CONSTRAINT fk_emprestimo_usuario
    FOREIGN KEY (remetente_id)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);
