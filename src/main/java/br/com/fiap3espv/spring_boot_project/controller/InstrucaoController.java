package br.com.fiap3espv.spring_boot_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap3espv.spring_boot_project.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espv.spring_boot_project.instrucao.DadosCancelamentoInstrucao;
import br.com.fiap3espv.spring_boot_project.instrucao.DadosDetalhamentoInstrucao;
import br.com.fiap3espv.spring_boot_project.instrucao.InstrucaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    private final InstrucaoService instrucaoService;

    public InstrucaoController(InstrucaoService instrucaoService) {
        this.instrucaoService = instrucaoService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoInstrucao> agendar(@RequestBody @Valid DadosAgendamentoInstrucao dados) {
        var instrucao = instrucaoService.agendar(dados);
        return ResponseEntity.ok(instrucao);
    }

    @PutMapping("/cancelar")
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoInstrucao dados) {
        instrucaoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
