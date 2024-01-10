package br.com.alura.clinic.manager.domain.consulta;

import br.com.alura.clinic.manager.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        // @JsonAlias(“dataConsulta”) OU @JsonAlias({“dataConsulta”, “data_consulta”})
        // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Especialidade especialidade) {
}
