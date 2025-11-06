package br.com.fiap3espv.spring_boot_project.instrucao;

import br.com.fiap3espv.spring_boot_project.aluno.Aluno;
import br.com.fiap3espv.spring_boot_project.aluno.AlunoRepository;
import br.com.fiap3espv.spring_boot_project.instrutor.Instrutor;
import br.com.fiap3espv.spring_boot_project.instrutor.InstrutorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Random;

@Service
public class InstrucaoService {

    private final InstrucaoRepository instrucaoRepository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;

    public InstrucaoService(InstrucaoRepository instrucaoRepository, AlunoRepository alunoRepository, InstrutorRepository instrutorRepository) {
        this.instrucaoRepository = instrucaoRepository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
    }

    @Transactional
    public DadosDetalhamentoInstrucao agendar(DadosAgendamentoInstrucao dados) {
        Aluno aluno = alunoRepository.findById(dados.idAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        if (!aluno.getAtivo())
            throw new IllegalArgumentException("Não é possível agendar instrução para aluno inativo.");

        Instrutor instrutor = null;
        if (dados.idInstrutor() != null) {
            instrutor = instrutorRepository.findById(dados.idInstrutor())
                    .orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado"));
            if (!instrutor.getAtivo())
                throw new IllegalArgumentException("Não é possível agendar instrução com instrutor inativo.");
        }

        validarHorario(dados.dataHora());
        validarAgendamento(aluno, instrutor, dados.dataHora());

        // Escolha aleatória caso o instrutor seja nulo
        if (instrutor == null)
            instrutor = escolherInstrutorDisponivel(dados.dataHora());

        Instrucao instrucao = new Instrucao(aluno, instrutor, dados.dataHora());
        instrucaoRepository.save(instrucao);
        return new DadosDetalhamentoInstrucao(instrucao);
    }

    private void validarHorario(LocalDateTime dataHora) {
        LocalTime hora = dataHora.toLocalTime();
        DayOfWeek dia = dataHora.getDayOfWeek();

        if (dia == DayOfWeek.SUNDAY)
            throw new IllegalArgumentException("A auto-escola não funciona aos domingos.");

        if (hora.isBefore(LocalTime.of(6, 0)) || hora.isAfter(LocalTime.of(21, 0)))
            throw new IllegalArgumentException("O horário deve estar entre 06:00 e 21:00.");

        if (dataHora.isBefore(LocalDateTime.now().plusMinutes(30)))
            throw new IllegalArgumentException("A instrução deve ser agendada com antecedência mínima de 30 minutos.");
    }

    private void validarAgendamento(Aluno aluno, Instrutor instrutor, LocalDateTime dataHora) {
        long aulasDia = instrucaoRepository.countByAlunoAndData(aluno.getId(), dataHora);
        if (aulasDia >= 2)
            throw new IllegalArgumentException("O aluno já possui duas instruções neste dia.");

        if (instrutor != null && instrucaoRepository.countByInstrutorAndDataHora(instrutor.getId(), dataHora) > 0)
            throw new IllegalArgumentException("O instrutor já possui uma instrução neste horário.");
    }

    private Instrutor escolherInstrutorDisponivel(LocalDateTime dataHora) {
        List<Instrutor> disponiveis = instrutorRepository.findAll()
                .stream()
                .filter(i -> i.getAtivo() &&
                        instrucaoRepository.countByInstrutorAndDataHora(i.getId(), dataHora) == 0)
                .toList();

        if (disponiveis.isEmpty())
            throw new IllegalArgumentException("Nenhum instrutor disponível para o horário informado.");

        return disponiveis.get(new Random().nextInt(disponiveis.size()));
    }

    @Transactional
    public void cancelar(DadosCancelamentoInstrucao dados) {
        Instrucao instrucao = instrucaoRepository.findById(dados.idInstrucao())
                .orElseThrow(() -> new EntityNotFoundException("Instrução não encontrada."));

        if (Duration.between(LocalDateTime.now(), instrucao.getDataHora()).toHours() < 24)
            throw new IllegalArgumentException("Instruções só podem ser canceladas com 24h de antecedência.");

        instrucao.cancelar(dados.motivo());
    }
}
