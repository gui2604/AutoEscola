package br.com.fiap3espv.spring_boot_project.instrucao;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoInstrucao(
        @NotNull Long idInstrucao,
        @NotNull MotivoCancelamento motivo
) {}
