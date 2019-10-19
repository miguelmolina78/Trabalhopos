CREATE TABLE professor (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  salario FLOAT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE curso (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  dataExpirou DATE NULL,
  valorMensalidade FLOAT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE turma (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  curso_id INTEGER UNSIGNED NOT NULL,
  dataInicioInscricao DATE NULL,
  dataFinalInscricao DATE NULL,
  dataInicioAulas DATE NULL,
  dataFinalAulas DATE NULL,
  PRIMARY KEY(id),
  INDEX Turma_FKIndex1(curso_id),
  FOREIGN KEY(curso_id)
    REFERENCES curso(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE materia (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  curso_id INTEGER UNSIGNED NOT NULL,
  professor_id INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(50) NOT NULL,
  PRIMARY KEY(id),
  INDEX Materia_FKIndex1(professor_id),
  INDEX Materia_FKIndex2(curso_id),
  FOREIGN KEY(professor_id)
    REFERENCES professor(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(curso_id)
    REFERENCES curso(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE aluno (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  turma_id INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(100) NOT NULL,
  dataNascimento DATE NOT NULL,
  PRIMARY KEY(id),
  INDEX aluno_FKIndex1(turma_id),
  FOREIGN KEY(turma_id)
    REFERENCES turma(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

