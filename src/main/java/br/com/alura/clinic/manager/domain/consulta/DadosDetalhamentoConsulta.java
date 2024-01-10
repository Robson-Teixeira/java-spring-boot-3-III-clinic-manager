package br.com.alura.clinic.manager.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
