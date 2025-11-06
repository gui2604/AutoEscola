package br.com.fiap3espv.spring_boot_project.instrucao;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoInstrucao(
        @NotNull Long idAluno,
        Long idInstrutor,
        @NotNull @Future LocalDateTime dataHora
) {}
