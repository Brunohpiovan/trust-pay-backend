ALTER TABLE usuario
CHANGE COLUMN chavePix chave_pix VARCHAR(255) NOT NULL;

ALTER TABLE usuario
DROP INDEX unique_usuario_chavePix,
ADD CONSTRAINT unique_usuario_chave_pix UNIQUE (chave_pix);
