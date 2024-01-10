package br.com.alura.clinic.manager.controller;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        return ResponseEntity.ok(
                new DadosDetalhamentoConsulta(null, null, null, null));
    }

}