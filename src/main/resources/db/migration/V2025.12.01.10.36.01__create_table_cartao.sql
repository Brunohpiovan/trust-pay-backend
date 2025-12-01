CREATE TABLE cartao (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

  numero VARCHAR(20) NOT NULL,
  nomeTitular VARCHAR(100) NOT NULL,
  validade VARCHAR(5) NOT NULL,
  cvv VARCHAR(255),

  tipo ENUM('CREDITO', 'DEBITO') NOT NULL,

  usuario_id BIGINT NOT NULL,

  bloqueado BIT(1),

  CONSTRAINT fk_cartao_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);
