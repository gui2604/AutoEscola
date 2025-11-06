CREATE TABLE instrucoes (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    instrutor_id BIGINT,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    motivo_cancelamento VARCHAR(30),
    CONSTRAINT fk_instrucao_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    CONSTRAINT fk_instrucao_instrutor FOREIGN KEY (instrutor_id) REFERENCES instrutores(id)
);
