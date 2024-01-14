package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var dataConsulta = dadosAgendamentoConsulta.data();
        var dataAgora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com antecedência de 30 minutos!!");

    }

}
