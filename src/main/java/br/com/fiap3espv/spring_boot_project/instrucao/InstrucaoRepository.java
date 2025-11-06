package br.com.fiap3espv.spring_boot_project.instrucao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {

    @Query("SELECT COUNT(i) FROM Instrucao i WHERE i.aluno.id = :idAluno AND DATE(i.dataHora) = DATE(:dataHora)")
    long countByAlunoAndData(Long idAluno, LocalDateTime dataHora);

    @Query("SELECT COUNT(i) FROM Instrucao i WHERE i.instrutor.id = :idInstrutor AND i.dataHora = :dataHora AND i.status = 'AGENDADA'")
    long countByInstrutorAndDataHora(Long idInstrutor, LocalDateTime dataHora);
}
