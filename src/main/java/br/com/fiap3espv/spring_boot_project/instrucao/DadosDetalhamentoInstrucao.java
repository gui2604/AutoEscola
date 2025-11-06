package br.com.fiap3espv.spring_boot_project.instrucao;

import java.time.LocalDateTime;

public record DadosDetalhamentoInstrucao(
        Long id,
        String aluno,
        String instrutor,
        LocalDateTime dataHora,
        StatusInstrucao status,
        MotivoCancelamento motivoCancelamento
) {
    public DadosDetalhamentoInstrucao(Instrucao instrucao) {
        this(instrucao.getId(),
             instrucao.getAluno().getNome(),
             instrucao.getInstrutor() != null ? instrucao.getInstrutor().getNome() : "Não definido",
             instrucao.getDataHora(),
             instrucao.getStatus(),
             instrucao.getMotivoCancelamento());
    }
}
