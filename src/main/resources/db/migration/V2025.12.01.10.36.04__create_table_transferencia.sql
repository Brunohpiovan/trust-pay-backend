CREATE TABLE transferencia (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

  remetente_id BIGINT NOT NULL,
  destinatario_id BIGINT NOT NULL,

  data_hora DATETIME NOT NULL,

  valor DECIMAL(19,2) NOT NULL,

  CONSTRAINT fk_transferencia_remetente
    FOREIGN KEY (remetente_id)
    REFERENCES usuario(id)
    ON DELETE CASCADE,

  CONSTRAINT fk_transferencia_destinatario
    FOREIGN KEY (destinatario_id)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);
