package br.com.fiap3espv.spring_boot_project.instrucao;

import java.time.LocalDateTime;

import br.com.fiap3espv.spring_boot_project.aluno.Aluno;
import br.com.fiap3espv.spring_boot_project.instrutor.Instrutor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "instrucoes")
@Entity(name = "Instrucao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Aluno aluno;

    @ManyToOne
    private Instrutor instrutor;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusInstrucao status;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public Instrucao(Aluno aluno, Instrutor instrutor, LocalDateTime dataHora) {
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.dataHora = dataHora;
        this.status = StatusInstrucao.AGENDADA;
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.status = StatusInstrucao.CANCELADA;
        this.motivoCancelamento = motivo;
    }
}
