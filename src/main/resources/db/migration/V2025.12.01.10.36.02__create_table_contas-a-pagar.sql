CREATE TABLE contas_pagar (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

  valor DECIMAL(19,2) NOT NULL,

  remetente_id BIGINT NOT NULL,

  vencimento DATE NOT NULL,

  numero_parcela INT NOT NULL,

  situacao_contas ENUM('ABERTA', 'PAGA', 'ATRASADA') NOT NULL,

  CONSTRAINT fk_contas_pagar_usuario
    FOREIGN KEY (remetente_id)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);
