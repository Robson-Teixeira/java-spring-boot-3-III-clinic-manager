package br.com.alura.clinic.manager.domain.consulta.validacoes.agendamento;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var dataConsulta = dadosAgendamentoConsulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var aberturaClinica = dataConsulta.getHour() < 7;
        var fechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || aberturaClinica || fechamentoClinica)
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");

    }

}
